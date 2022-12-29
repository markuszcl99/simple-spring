package com.markus.spring.beans.factory.support;

import com.markus.spring.beans.factory.config.BeanDefinition;
import com.markus.spring.beans.factory.config.ConfigurableListableBeanFactory;

import java.util.Map;

/**
 * @author: markus
 * @date: 2022/12/25 9:11 PM
 * @Description: IoC框架唯一BeanFactory实现类
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements ConfigurableListableBeanFactory,BeanDefinitionRegistry {

    //---------------------------------------------------------------------
    // Implementation of AbstractBeanFactory interface
    //---------------------------------------------------------------------
    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        return null;
    }

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) {
        return null;
    }

    //---------------------------------------------------------------------
    // Implementation of ConfigurableListableBeanFactory interface
    //---------------------------------------------------------------------
    @Override
    public void preInstantiationSingletons() {

    }

    //---------------------------------------------------------------------
    // Implementation of ConfigurableBeanFactory interface
    //---------------------------------------------------------------------
    @Override
    public void destroySingletons() {

    }

    //---------------------------------------------------------------------
    // Implementation of ListableBeanFactory interface
    //---------------------------------------------------------------------
    @Override
    public <T> T getBean(Class<T> requiredType) {
        return null;
    }

    //---------------------------------------------------------------------
    // Implementation of ListableBeanFactory interface
    //---------------------------------------------------------------------
    @Override
    public boolean containsBean(String name) {
        return false;
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return new String[0];
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) {
        return null;
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {

    }

    @Override
    public int getBeanDefinitionCount() {
        return 0;
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return false;
    }
}
