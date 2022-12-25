package com.markus.spring.beans.factory;

/**
 * @author: markus
 * @date: 2022/12/25 4:59 PM
 * @Description: IoC容器工厂
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public interface BeanFactory {
    /**
     * 根据Bean名称查找Bean对象
     *
     * @param name
     * @return
     */
    Object getBean(String name);

    /**
     * 根据参数 创建一个对应类型的Bean实例（慎用）
     * @param name
     * @param args
     * @return
     */
    Object getBean(String name, Object[] args);

    /**
     * 根据Bean名称、目标对象类型查找Bean实例(指定类型，用户获取无需再进行强转)
     *
     * @param name
     * @param requiredType
     * @param <T>
     * @return
     */
    <T> T getBean(String name, Class<T> requiredType);

    /**
     * 根据目标对象类型查找指定类型Bean实例
     *
     * @param requiredType
     * @param <T>
     * @return
     */
    <T> T getBean(Class<T> requiredType);

    /**
     * IoC容器中是否包含指定名称的Bean
     * @param name
     * @return
     */
    boolean containsBean(String name);
}
