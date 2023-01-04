package com.markus.spring.beans.factory.config;

/**
 * @author: markus
 * @date: 2023/1/4 10:33 PM
 * @Description:
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public interface SmartInstantiationAwareBeanPostProcessor extends InstantiationAwareBeanPostProcessor {
    /**
     * 获取对指定bean的早期访问的引用，通常用于解析循环引用
     *
     * @param bean
     * @param beanName
     * @return
     */
    default Object getEarlyBeanReference(Object bean, String beanName) {
        return bean;
    }
}
