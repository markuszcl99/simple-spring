package com.markus.spring.context;

import java.util.EventObject;

/**
 * @author: markus
 * @date: 2023/1/16 9:59 PM
 * @Description: 容器事件
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public abstract class ApplicationEvent extends EventObject {

    private static final long serialVersionUID = 7099057708183571937L;

    private final long timestamp;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationEvent(Object source) {
        super(source);
        this.timestamp = System.currentTimeMillis();
    }

    public long getTimestamp() {
        return timestamp;
    }
}
