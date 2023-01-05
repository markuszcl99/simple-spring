package com.markus.spring.beans.factory;

/**
 * @author: markus
 * @date: 2023/1/5 10:27 PM
 * @Description: Bean名称注入
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public interface BeanNameAware extends Aware {
    void setBeanName(String beanName);
}
