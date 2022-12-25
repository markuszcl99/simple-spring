package com.markus.spring.beans.factory.config;

import com.markus.spring.beans.factory.BeanFactory;
import com.markus.spring.beans.factory.HierarchicalBeanFactory;
import com.markus.spring.beans.factory.config.BeanPostProcessor;

/**
 * @author: markus
 * @date: 2022/12/25 5:37 PM
 * @Description: Configuration interface to be implemented by most bean factories. Provides facilities to configure a bean factory, in addition to the bean factory client methods in the BeanFactory interface.
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory {

    /**
     * 单例
     */
    String SCOPE_SINGLETON = "singleton";

    /**
     * 原型
     */
    String SCOPE_PROTOTYPE = "prototype";

    /**
     * 添加一个新的BeanPostProcessor，它将应用于此工厂创建的bean。在工厂配置期间调用
     * @param beanPostProcessor
     */
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /**
     * 销毁所有的单例Bean
     */
    void destroySingletons();
}
