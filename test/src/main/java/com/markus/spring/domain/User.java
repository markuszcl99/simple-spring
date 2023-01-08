package com.markus.spring.domain;

import com.markus.spring.beans.factory.*;
import com.markus.spring.enums.City;

/**
 * @author: markus
 * @date: 2022/12/29 11:45 PM
 * @Description:
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class User implements BeanNameAware, BeanFactoryAware, BeanClassLoaderAware, InitializingBean, DisposableBean {
    private String username;
    private String sex;
    private Integer age;
    private boolean success;
    private City city;
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

    public void customInitMethod() {
        System.out.println("I am custom init method!");
    }

    public void customDestroyMethod() {
        System.out.println("I am custom destroy method!");
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", success=" + success +
                ", city=" + city +
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

    @Override
    public void afterPropertiesSet() {
        System.out.println("implement InitializingBean's afterPropertiesSet method!");
    }

    @Override
    public void destroy() {
        System.out.println("implement DisposableBean's destroy method!");
    }
}
