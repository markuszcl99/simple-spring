package com.markus.spring.beans.factory.config;

import com.markus.spring.beans.MutablePropertyValues;
import com.sun.istack.internal.Nullable;

/**
 * @author: markus
 * @date: 2022/12/25 5:48 PM
 * @Description:
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public interface BeanDefinition {

    String SCORE_SINGLETON = ConfigurableBeanFactory.SCOPE_SINGLETON;

    String SCOPE_PROTOTYPE = ConfigurableBeanFactory.SCOPE_PROTOTYPE;

    void setParentName(String parentName);

    @Nullable
    String getParentName();

    void setBeanClassName(String beanClassName);

    @Nullable
    String getBeanClassName();

    void setScope(@Nullable String scope);

    @Nullable
    String getScope();

    void setLazyInit(boolean lazyInit);

    boolean isLazyInit();

    void setPrimary(boolean primary);

    boolean isPrimary();

    boolean isSingleton();

    boolean isPrototype();

    MutablePropertyValues getPropertyValues();

    default boolean hasPropertyValues() {
        return !getPropertyValues().isEmpty();
    }
}
