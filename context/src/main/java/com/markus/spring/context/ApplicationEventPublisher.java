package com.markus.spring.context;

/**
 * @author: markus
 * @date: 2023/1/16 10:02 PM
 * @Description: 事件发布器
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public interface ApplicationEventPublisher {
    void publishedEvent(ApplicationEvent applicationEvent);
}
