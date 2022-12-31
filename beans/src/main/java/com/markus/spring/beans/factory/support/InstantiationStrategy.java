package com.markus.spring.beans.factory.support;

import com.markus.spring.beans.factory.BeanFactory;

import java.lang.reflect.Constructor;

/**
 * @author: markus
 * @date: 2022/12/30 10:49 PM
 * @Description: 实例化策略 接口
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public interface InstantiationStrategy {
    /**
     * 实例化
     *
     * @param mbd
     * @param beanName
     * @param beanFactory
     * @return
     */
    Object instantiate(RootBeanDefinition mbd, String beanName, BeanFactory beanFactory, final Constructor<?> ctor, Object... args);
}
