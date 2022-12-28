package com.markus.spring.beans.factory.support;

import com.markus.spring.beans.factory.config.BeanDefinition;
import com.sun.istack.internal.Nullable;

/**
 * @author: markus
 * @date: 2022/12/25 9:35 PM
 * @Description: 通用BeanDefinition
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class GenericBeanDefinition extends AbstractBeanDefinition {

    @Nullable
    private String parentName;

    public GenericBeanDefinition() {
        super();
    }

    public GenericBeanDefinition(BeanDefinition original) {
        super(original);
    }

    @Override
    public void setParentName(@Nullable String parentName) {
        this.parentName = parentName;
    }

    @Override
    public String getParentName() {
        return this.parentName;
    }
}
