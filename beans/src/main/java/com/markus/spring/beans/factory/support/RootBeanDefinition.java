package com.markus.spring.beans.factory.support;

import com.sun.istack.internal.Nullable;

/**
 * @author: markus
 * @date: 2022/12/25 9:35 PM
 * @Description: 根BeanDefinition
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class RootBeanDefinition extends AbstractBeanDefinition{
    @Override
    public String getParentName() {
        return null;
    }

    @Override
    public void setParentName(@Nullable String parentName) {
        if (parentName != null) {
            throw new IllegalArgumentException("Root bean cannot be changed into a child bean with parent reference");
        }
    }
}
