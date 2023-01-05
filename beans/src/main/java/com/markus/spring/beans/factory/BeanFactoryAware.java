package com.markus.spring.beans.factory;

/**
 * @author: markus
 * @date: 2023/1/5 10:28 PM
 * @Description: BeanFactory 回调注入
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public interface BeanFactoryAware extends Aware {
    void setBeanFactory(BeanFactory beanFactory);
}
