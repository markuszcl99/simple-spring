package com.markus.spring.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.markus.spring.beans.BeansException;
import com.markus.spring.beans.PropertyValue;
import com.markus.spring.beans.PropertyValues;
import com.markus.spring.beans.factory.*;
import com.markus.spring.beans.factory.config.*;
import com.sun.istack.internal.Nullable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author: markus
 * @date: 2022/12/25 9:10 PM
 * @Description:
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

    /**
     * 是否允许循环引用
     */
    private boolean allowCircularReferences = true;

    @Override
    public Object applyBeanPostProcessorBeforeInstantiation(Object existBean, String beanName) {
        Object result = existBean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            Object current = beanPostProcessor.postProcessBeforeInitialization(result, beanName);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
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
        // 是否需要提前将bean引用曝光到容器中，此处开始便是解决单例Bean循环依赖的地方
        boolean earlySingletonExposure = mbd.isSingleton() && this.allowCircularReferences;
        // todo spring 这块判断是否需要早期曝光还有一个判断条件，此时我们先不实现
//        &&
//                isSingletonCurrentlyInCreation(beanName));
        if (earlySingletonExposure) {
            addSingletonFactory(beanName, () -> getEarlyBeanReference(beanName, mbd, bean));
        }
        populateBean(beanName, bean, mbd);
        exposedObject = initializeBean(beanName, exposedObject, mbd);

        // 注册单例Bean的销毁回调
        registerDisposableBeanIfNecessary(beanName, bean, mbd);
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

    private Object initializeBean(String beanName, Object exposedObject, RootBeanDefinition mbd) {
        // Aware接口方法注入
        invokeAwareMethod(beanName, exposedObject);
        Object wrappedBean = exposedObject;
        if (mbd == null || !mbd.isSynthetic()) {
            wrappedBean = applyBeanPostProcessorBeforeInstantiation(wrappedBean, beanName);
        }

        try {
            invokeInitMethods(beanName, wrappedBean, mbd);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new BeansException("user custom init method invoke error!");
        }

        if (wrappedBean == null || !mbd.isSynthetic()) {
            wrappedBean = applyBeanPostProcessorAfterInstantiation(wrappedBean, beanName);
        }
        return wrappedBean;
    }

    protected void invokeInitMethods(String beanName, Object bean, RootBeanDefinition mbd) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (bean instanceof InitializingBean) {
            ((InitializingBean) bean).afterPropertiesSet();
        }
        String initMethodName = mbd.getInitMethodName();
        if (StrUtil.isNotEmpty(initMethodName)) {
            Method initMethod = mbd.getBeanClass().getMethod(initMethodName);
            if (initMethod == null) {
                throw new BeansException("user define init-method not be found!");
            }
            initMethod.invoke(bean);
        }
    }

    protected void registerDisposableBeanIfNecessary(String beanName, Object bean, RootBeanDefinition mbd) {
        if (!mbd.isPrototype()) {
            if (mbd.isSingleton()) {
                registerDisposableBean(beanName, new DisposableBeanAdapter(bean, beanName, mbd));
            }
            // 其他作用域的bean也有相应的处理，这里先不实现了
        }
    }


    private void invokeAwareMethod(final String beanName, final Object bean) {
        if (bean instanceof Aware) {
            if (bean instanceof BeanNameAware) {
                ((BeanNameAware) bean).setBeanName(beanName);
            }
            if (bean instanceof BeanClassLoaderAware) {
                ((BeanClassLoaderAware) bean).setBeanClassLoader(getBeanClassLoader());
            }
            if (bean instanceof BeanFactoryAware) {
                ((BeanFactoryAware) bean).setBeanFactory(AbstractAutowireCapableBeanFactory.this);
            }
        }
    }

    protected void applyPropertyValues(String beanName, RootBeanDefinition mbd, Object bean, PropertyValues pvs) {
        for (PropertyValue pv : pvs.getPropertyValues()) {
            String name = pv.getName();
            Object value = pv.getValue();
            if (value instanceof BeanReference) {
                BeanReference br = (BeanReference) value;
                value = getBean(br.getBeanName());
            }
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

    protected Object getEarlyBeanReference(String beanName, RootBeanDefinition mbd, Object bean) {
        Object exposedObject = bean;
        // todo spring 这里还做了一些限制，这里先不实现了
        if (hasInstantiationAwareBeanPostProcessors()) {
            for (BeanPostProcessor bp : getBeanPostProcessors()) {
                if (bp instanceof SmartInstantiationAwareBeanPostProcessor) {
                    SmartInstantiationAwareBeanPostProcessor sbp = (SmartInstantiationAwareBeanPostProcessor) bp;
                    exposedObject = sbp.getEarlyBeanReference(exposedObject, beanName);
                }
            }
        }
        return exposedObject;
    }
}
