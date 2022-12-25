package com.markus.spring.beans.factory;

/**
 * @author: markus
 * @date: 2022/12/25 10:17 PM
 * @Description: 定义一个对象工厂，可以返回对象
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@FunctionalInterface
public interface ObjectFactory<T> {

    T getObject();
}
