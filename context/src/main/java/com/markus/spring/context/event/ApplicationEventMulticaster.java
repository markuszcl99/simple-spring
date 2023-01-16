package com.markus.spring.context.event;

import com.markus.spring.context.ApplicationEvent;
import com.markus.spring.context.ApplicationListener;

/**
 * @author: markus
 * @date: 2023/1/16 10:13 PM
 * @Description: 事件广播器
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public interface ApplicationEventMulticaster {

    /**
     * 增加事件监听器
     * @param applicationListener
     */
    void addApplicationListener(ApplicationListener<?> applicationListener);

    /**
     * 删除事件监听器
     * @param applicationListener
     */
    void removeApplicationListener(ApplicationListener<?> applicationListener);

    /**
     * 事件广播
     * @param applicationEvent
     */
    void multicasterEvent(ApplicationEvent applicationEvent);
}
