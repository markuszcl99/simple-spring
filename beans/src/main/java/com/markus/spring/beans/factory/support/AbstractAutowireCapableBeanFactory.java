package com.markus.spring.beans.factory.support;

import com.markus.spring.beans.factory.AutowireCapableBeanFactory;
import com.markus.spring.beans.factory.config.BeanDefinition;
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
        return bean;
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
