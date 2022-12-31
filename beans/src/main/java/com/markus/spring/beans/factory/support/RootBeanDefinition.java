package com.markus.spring.beans.factory.support;

import com.markus.spring.beans.factory.config.BeanDefinition;
import com.sun.istack.internal.Nullable;

/**
 * @author: markus
 * @date: 2022/12/25 9:35 PM
 * @Description: 根BeanDefinition
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class RootBeanDefinition extends AbstractBeanDefinition {

    /**
     * 该字段的作用是用来标记BeanDefinition是否稳定，如果为true则需要进行合并操作，否则不需要
     * todo Spring在合并BeanDefinition后会进行该字段的标记，我们这里先默认为false，不标记，认为所有Bean定义都是一个独立的个体，候选完善parent时再将此处改进
     */
    volatile boolean stale = false;

    public RootBeanDefinition() {
        super();
    }

    public RootBeanDefinition(@Nullable Class<?> beanClass) {
        super();
        setBeanClass(beanClass);
    }

    public RootBeanDefinition(BeanDefinition original) {
        super(original);
    }


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
