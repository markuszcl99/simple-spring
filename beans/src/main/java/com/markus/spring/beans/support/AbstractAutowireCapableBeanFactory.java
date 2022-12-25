package com.markus.spring.beans.support;

import com.markus.spring.beans.factory.AutowireCapableBeanFactory;

/**
 * @author: markus
 * @date: 2022/12/25 9:10 PM
 * @Description:
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    @Override
    public Object applyBeanPostProcessorBeforeInstantiation(Object existBean, String beanName) {
        return null;
    }

    @Override
    public Object applyBeanPostProcessorAfterInstantiation(Object existBean, String beanName) {
        return null;
    }
}
