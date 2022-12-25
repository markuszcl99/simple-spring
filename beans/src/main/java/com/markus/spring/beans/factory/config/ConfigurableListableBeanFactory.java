package com.markus.spring.beans.factory.config;

import com.markus.spring.beans.factory.AutowireCapableBeanFactory;
import com.markus.spring.beans.factory.ListableBeanFactory;

/**
 * @author: markus
 * @date: 2022/12/25 5:38 PM
 * @Description: 大多数可列表bean工厂要实现的配置接口。除了ConfigurationBeanFactory之外，它还提供了分析和修改bean定义以及预实例化单例的工具
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public interface ConfigurableListableBeanFactory extends ConfigurableBeanFactory, ListableBeanFactory, AutowireCapableBeanFactory {


    /**
     * 根据Bean名称获得BeanDefinition
     * @param beanName
     * @return
     */
    BeanDefinition getBeanDefinition(String beanName);

    /**
     * 确保所有非延迟加载的Bean都被初始化，包括FactoryBean的。
     */
    void preInstantiationSingletons();
}
