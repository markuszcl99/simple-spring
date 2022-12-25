package com.markus.spring.beans.factory;

import java.util.Map;

/**
 * @author: markus
 * @date: 2022/12/25 5:25 PM
 * @Description: BeanFactory扩展，支持对指定类型所有Bean的枚举，而非一对一。
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public interface ListableBeanFactory extends BeanFactory{

    /**
     * 获取指定类型的所有Bean实例
     * @param type
     * @param <T>
     * @return
     */
    <T> Map<String,T> getBeansOfType(Class<T> type);

    /**
     * 获取注册表中所有的Bean名称
     * @return
     */
    String[] getBeanDefinitionNames();
}
