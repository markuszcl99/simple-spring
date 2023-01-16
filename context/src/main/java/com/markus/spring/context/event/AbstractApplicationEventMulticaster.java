package com.markus.spring.context.event;

import com.markus.spring.context.ApplicationEvent;
import com.markus.spring.context.ApplicationListener;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author: markus
 * @date: 2023/1/16 10:15 PM
 * @Description: 事件广播器抽象实现
 * todo 该类我们先简单实现了，没有考虑并发的情况
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster {

    private Set<ApplicationListener<?>> applicationListeners = new LinkedHashSet<>();

    @Override
    public void addApplicationListener(ApplicationListener<?> applicationListener) {
        applicationListeners.add(applicationListener);
    }

    @Override
    public void removeApplicationListener(ApplicationListener<?> applicationListener) {
        applicationListeners.remove(applicationListener);
    }

    protected Collection<ApplicationListener<?>> getApplicationListeners() {
        return this.applicationListeners;
    }
}
