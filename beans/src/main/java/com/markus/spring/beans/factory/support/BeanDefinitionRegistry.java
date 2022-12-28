package com.markus.spring.beans.factory.support;

import com.markus.spring.beans.factory.config.BeanDefinition;

/**
 * @author: markus
 * @date: 2022/12/26 10:18 PM
 * @Description: BeanDefinition 注册表
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    int getBeanDefinitionCount();
}
