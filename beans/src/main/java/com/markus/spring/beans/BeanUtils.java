package com.markus.spring.beans;

import com.markus.spring.core.util.Assert;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author: markus
 * @date: 2022/12/28 11:38 PM
 * @Description:
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public abstract class BeanUtils {
    public static <T> T instantiateClass(Class<T> clazz) {
        Assert.notNull(clazz, "Class must not be null");
        // todo spring框架内部使用各种复杂的逻辑创建实例，我们暂时不实现这么多了，默认clazz就是有效的
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeansException("class newInstance error");
        }
    }
}
