package com.markus.spring.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.markus.spring.beans.PropertyValue;
import com.markus.spring.beans.PropertyValues;
import com.markus.spring.beans.factory.AutowireCapableBeanFactory;
import com.markus.spring.beans.factory.config.BeanDefinition;
import com.markus.spring.beans.factory.config.BeanPostProcessor;
import com.markus.spring.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.sun.istack.internal.Nullable;

import java.lang.reflect.Constructor;

/**
 * @author: markus
 * @date: 2022/12/25 9:10 PM
 * @Description:
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

    @Override
    public Object applyBeanPostProcessorBeforeInstantiation(Object existBean, String beanName) {
        return null;
    }

    @Override
    public Object applyBeanPostProcessorAfterInstantiation(Object existBean, String beanName) {
        return null;
    }

    //---------------------------------------------------------------------
    // Implementation of relevant AbstractBeanFactory template methods
    //---------------------------------------------------------------------
    @Override
    protected Object createBean(String beanName, RootBeanDefinition mbd, Object[] args) {
        RootBeanDefinition mbdToUse = mbd;

        return doCreateBean(beanName, mbdToUse, args);
    }

    protected Object doCreateBean(final String beanName, final RootBeanDefinition mbd, final @Nullable Object[] args) {
        Object bean = createBeanInstance(mbd, beanName, args);

        // 初始化 Bean
        Object exposedObject = bean;
        populateBean(beanName, bean, mbd);
        return bean;
    }

    private void populateBean(String beanName, Object bean, RootBeanDefinition mbd) {
        // 在Bean属性赋值前，给任何InstantiationAwareBeanPostProcessor一次机会去修改Bean的状态
        if (hasInstantiationAwareBeanPostProcessors()) {
            for (BeanPostProcessor bp : getBeanPostProcessors()) {
                if (bp instanceof InstantiationAwareBeanPostProcessor) {
                    InstantiationAwareBeanPostProcessor ibp = (InstantiationAwareBeanPostProcessor) bp;
                    if (!ibp.postProcessAfterInstantiation(bean, beanName)) {
                        return;
                    }

                }
            }
        }

        PropertyValues pvs = mbd.hasPropertyValues() ? mbd.getPropertyValues() : null;

        boolean hasInstAwareBps = hasInstantiationAwareBeanPostProcessors();

        if (hasInstAwareBps) {
            for (BeanPostProcessor bp : getBeanPostProcessors()) {
                if (bp instanceof InstantiationAwareBeanPostProcessor) {
                    InstantiationAwareBeanPostProcessor ipb = (InstantiationAwareBeanPostProcessor) bp;
                    PropertyValues propertyToUse = ipb.postProcessProperties(pvs, bean, beanName);
                    if (propertyToUse == null) {
                        // todo 这里Spring还定义了InstantiationAwareBeanPostProcessor#postProcessPropertyValues(xxx) 这里我们就先不实现了
                    }
                    pvs = propertyToUse;
                }
            }
        }

        if (pvs != null) {
            applyPropertyValues(beanName, mbd, bean, pvs);
        }
    }

    protected void applyPropertyValues(String beanName, RootBeanDefinition mbd, Object bean, PropertyValues pvs) {
        for (PropertyValue pv : pvs.getPropertyValues()) {
            // todo 这里先不考虑 Bean引用 后续实现
            String name = pv.getName();
            Object value = pv.getValue();
            // 反射设置属性填充
            BeanUtil.setProperty(bean, name, value);
        }
    }

    private Object createBeanInstance(RootBeanDefinition mbd, String beanName, Object[] args) {
        // 选择一个可用的构造器去创建示例
        Constructor constructorToUse = null;
        Class<?> clazz = mbd.getBeanClass();
        Constructor[] allConstructors = clazz.getDeclaredConstructors();
        for (Constructor ctor : allConstructors) {
            if (args != null && ctor.getParameterTypes().length == args.length) {
                constructorToUse = ctor;
                break;
            }
        }

        return getInstantiationStrategy().instantiate(mbd, beanName, this, constructorToUse, args);
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return this.instantiationStrategy;
    }
}
