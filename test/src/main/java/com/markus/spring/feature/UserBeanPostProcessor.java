package com.markus.spring.feature;

import com.markus.spring.beans.BeansException;
import com.markus.spring.beans.factory.config.BeanPostProcessor;

/**
 * @author: markus
 * @date: 2023/1/5 11:03 PM
 * @Description: 对User类实例化进行扩展
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class UserBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("user".equals(beanName)) {
            System.out.println("user bean invoke BeanPostProcessor#postProcessBeforeInitialization");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if ("user".equals(beanName)) {
            System.out.println("user bean invoke BeanPostProcessor#postProcessAfterInitialization");
        }
        return bean;
    }
}
