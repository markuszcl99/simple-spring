package com.markus.spring.beans.factory;

/**
 * @author: markus
 * @date: 2023/1/6 10:09 PM
 * @Description: 自定义方法Bean回调接口
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public interface InitializingBean {
    /**
     * 用户实现，spring在初始化bean的流程中会进行回调
     */
    void afterPropertiesSet();
}
