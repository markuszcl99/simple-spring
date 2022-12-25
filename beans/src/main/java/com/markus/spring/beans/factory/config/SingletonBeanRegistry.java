package com.markus.spring.beans.factory.config;

/**
 * @author: markus
 * @date: 2022/12/25 9:39 PM
 * @Description: 单例Bean注册表
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public interface SingletonBeanRegistry {

    void registerSingleton(String beanName, Object singletonObject);

    Object getSingleton(String beanName);

    boolean containSingleton(String beanName);
}
