package com.markus.spring.context.event;

import com.markus.spring.context.ApplicationEvent;
import com.markus.spring.context.ApplicationListener;
import com.sun.istack.internal.Nullable;

import java.util.concurrent.Executor;

/**
 * @author: markus
 * @date: 2023/1/16 10:20 PM
 * @Description: 事件广播器默认实现
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

    public SimpleApplicationEventMulticaster() {

    }

    @Override
    public void multicasterEvent(ApplicationEvent applicationEvent) {
        for (ApplicationListener<?> listener : getApplicationListeners()) {
            doInvokeListener(listener, applicationEvent);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private void doInvokeListener(ApplicationListener applicationListener, ApplicationEvent event) {
        applicationListener.onApplicationEvent(event);
    }
}
