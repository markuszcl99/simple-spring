package com.markus.spring.beans.factory.support;

import com.markus.spring.beans.factory.config.BeanDefinition;
import com.markus.spring.beans.factory.config.ConfigurableListableBeanFactory;
import com.markus.spring.core.util.Assert;
import com.markus.spring.core.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: markus
 * @date: 2022/12/25 9:11 PM
 * @Description: IoC框架唯一BeanFactory实现类
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements ConfigurableListableBeanFactory, BeanDefinitionRegistry {

    /**
     * BeanDefinition Map
     */
    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);

    /**
     * BeanDefinition Register Order
     */
    private List<String> beanDefinitionNames = new ArrayList<>(256);

    //---------------------------------------------------------------------
    // Implementation of AbstractBeanFactory interface
    //---------------------------------------------------------------------
    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        return this.beanDefinitionMap.get(beanName);
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
        super.destroySingletons();
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
    public String[] getBeanDefinitionNames() {
        return StringUtils.toStringArray(this.beanDefinitionNames);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) {
        return null;
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        Assert.notNull(beanName, "beanName must not be null!");
        Assert.notNull(beanDefinition, "beanDefinition must not be null!");

        // todo Spring 这里会判断AbstractBeanDefinition的校验以及BeanDefinition是否可以被重写，通过配置来决定程序是否正常，我们这里暂时不考虑，直接注册
        this.beanDefinitionMap.put(beanName, beanDefinition);
        // BeanDefinition的注册顺序
        this.beanDefinitionNames.add(beanName);
    }

    @Override
    public int getBeanDefinitionCount() {
        return this.beanDefinitionMap.size();
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return this.beanDefinitionMap.containsKey(beanName);
    }
}
