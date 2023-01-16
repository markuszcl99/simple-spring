package com.markus.spring.context;

import java.util.EventListener;

/**
 * @author: markus
 * @date: 2023/1/16 10:03 PM
 * @Description: 事件监听器
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

    /**
     * 监听器接口
     * @param event
     */
    public void onApplicationEvent(E event);

}
