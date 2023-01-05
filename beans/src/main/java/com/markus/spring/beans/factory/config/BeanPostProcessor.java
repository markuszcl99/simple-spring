package com.markus.spring.beans.factory.config;

import com.markus.spring.beans.BeansException;
import com.sun.istack.internal.Nullable;

/**
 * @author: markus
 * @date: 2022/12/25 5:43 PM
 * @Description:
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public interface BeanPostProcessor {
    @Nullable
    default Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Nullable
    default Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
