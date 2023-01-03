package com.markus.spring.beans.factory.config;

/**
 * @author: markus
 * @date: 2023/1/3 10:45 PM
 * @Description: Bean引用
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class BeanReference {
    private String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
}
