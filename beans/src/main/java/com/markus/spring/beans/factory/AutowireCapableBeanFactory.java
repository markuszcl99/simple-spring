package com.markus.spring.beans.factory;

/**
 * @author: markus
 * @date: 2022/12/25 5:29 PM
 * @Description: BeanFactory拓展，具有注入能力
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public interface AutowireCapableBeanFactory extends BeanFactory{

    /**
     * Apply BeanPostProcessors to the given existing bean instance, invoking their postProcessBeforeInitialization methods. The returned bean instance may be a wrapper around the original.
     *
     * @param existBean
     * @param beanName
     * @return
     */
    Object applyBeanPostProcessorBeforeInstantiation(Object existBean, String beanName);

    /**
     * Apply BeanPostProcessors to the given existing bean instance, invoking their postProcessAfterInitialization methods. The returned bean instance may be a wrapper around the original.
     * @param existBean
     * @param beanName
     * @return
     */
    Object applyBeanPostProcessorAfterInstantiation(Object existBean, String beanName);
}
