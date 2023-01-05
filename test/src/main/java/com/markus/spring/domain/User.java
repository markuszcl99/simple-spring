package com.markus.spring.domain;

import com.markus.spring.beans.factory.BeanClassLoaderAware;
import com.markus.spring.beans.factory.BeanFactory;
import com.markus.spring.beans.factory.BeanFactoryAware;
import com.markus.spring.beans.factory.BeanNameAware;

/**
 * @author: markus
 * @date: 2022/12/29 11:45 PM
 * @Description:
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class User implements BeanNameAware, BeanFactoryAware, BeanClassLoaderAware {
    private String username;
    private String sex;
    private ClassLoader classLoader;
    private String beanName;
    private BeanFactory beanFactory;

    public User() {
    }

    public User(String username, String sex) {
        this.username = username;
        this.sex = sex;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public ClassLoader getClassLoader() {
        return classLoader;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public String getBeanName() {
        return beanName;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", sex='" + sex + '\'' +
                ", classLoader=" + classLoader +
                ", beanName='" + beanName + '\'' +
                ", beanFactory=" + beanFactory +
                '}';
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
}
