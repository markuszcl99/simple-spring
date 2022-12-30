package com.markus.spring.beans.factory.support;

import com.markus.spring.beans.factory.config.BeanDefinition;
import com.markus.spring.beans.factory.config.BeanPostProcessor;
import com.markus.spring.beans.factory.config.ConfigurableBeanFactory;
import com.sun.istack.internal.Nullable;

/**
 * @author: markus
 * @date: 2022/12/25 9:05 PM
 * @Description: BeanFactory抽象基类
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {


    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {

    }

    //---------------------------------------------------------------------
    // Implementation of BeanFactory interface
    //---------------------------------------------------------------------
    @Override
    public boolean containsBean(String name) {
        return false;
    }

    @Override
    public Object getBean(String name) {
        return doGetBean(name, null, null);
    }

    @Override
    public Object getBean(String name, Object[] args) {
        return doGetBean(name, null, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) {
        return doGetBean(name, requiredType, null);
    }

    protected <T> T doGetBean(final String name, @Nullable final Class<T> requiredType, @Nullable final Object[] args) {
        Object bean;
        Object sharedInstance = getSingleton(name);
        if (sharedInstance != null) {
            return (T) getObjectForBeanInstance(sharedInstance, name);
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        bean = createBean(name, beanDefinition, args);
        return (T) getObjectForBeanInstance(bean, name);
    }

    private Object getObjectForBeanInstance(Object beanInstance, String beanName) {
        // todo 后续完善FactoryBean
        return beanInstance;
    }

    //---------------------------------------------------------------------
    // Abstract methods to be implemented by subclasses
    //---------------------------------------------------------------------

    protected abstract BeanDefinition getBeanDefinition(String beanName);

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args);
}
