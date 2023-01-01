package com.markus.spring.beans.factory.config;

import com.markus.spring.beans.BeansException;
import com.markus.spring.beans.PropertyValues;
import com.sun.istack.internal.Nullable;

/**
 * @author: markus
 * @date: 2023/1/1 10:06 PM
 * @Description: Subinterface of BeanPostProcessor that adds a before-instantiation callback, and a callback after instantiation but before explicit properties are set or autowiring occurs.
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

    /**
     * 返回一个boolean值，决定当前Spring Bean是否需要进行属性填充
     *
     * @param bean
     * @param beanName
     * @return true 需要进行属性填充 false 略过属性填充
     * @throws BeansException
     */
    default boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        return true;
    }

    /**
     * 如果实现提供自定义postProcessPropertyValues实现，则应返回null（默认值），否则返回pvs。在该接口的未来版本中（删除了postProcessPropertyValues），默认实现将直接返回给定的pv。
     * @param pvs
     * @param bean
     * @param beanName
     * @return
     */
    @Nullable
    default PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) {
        return null;
    }
}
