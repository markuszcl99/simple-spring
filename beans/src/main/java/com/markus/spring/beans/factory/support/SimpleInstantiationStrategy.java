package com.markus.spring.beans.factory.support;

import com.markus.spring.beans.BeansException;
import com.markus.spring.beans.factory.BeanFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author: markus
 * @date: 2022/12/31 10:19 PM
 * @Description: 默认的jdk 反射实例化策略
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy {

    @Override
    public Object instantiate(RootBeanDefinition mbd, String beanName, BeanFactory beanFactory, Constructor<?> ctor, Object... args) {
        Class<?> clazz = mbd.getBeanClass();
        try {
            if (args != null) {
                return clazz.getConstructor(ctor.getParameterTypes()).newInstance(args);
            } else {
                return clazz.getDeclaredConstructor().newInstance();
            }
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException ex) {
            throw new BeansException("jdk reflect create instance fail!", ex);
        }
    }
}
