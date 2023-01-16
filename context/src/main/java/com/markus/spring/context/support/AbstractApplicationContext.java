package com.markus.spring.context.support;

import com.markus.spring.context.ApplicationEvent;
import com.markus.spring.context.ApplicationListener;
import com.markus.spring.context.ConfigurableApplicationContext;
import com.markus.spring.context.event.ApplicationEventMulticaster;
import com.markus.spring.context.event.SimpleApplicationEventMulticaster;
import com.sun.tools.javac.util.Assert;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author: markus
 * @date: 2023/1/12 11:30 PM
 * @Description: ApplicationContext抽象实现
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public abstract class AbstractApplicationContext implements ConfigurableApplicationContext {

    /**
     * 指定的监听器
     */
    private Set<ApplicationListener<?>> applicationListeners = new LinkedHashSet<>();

    private Set<ApplicationListener<?>> earlyApplicationListener;

    private Set<ApplicationEvent> earlyApplicationEvent;

    private ApplicationEventMulticaster applicationEventMulticaster;


    public AbstractApplicationContext() {
        // 这里先默认初始化了，后续实现refresh方法再移除
        this.applicationEventMulticaster = new SimpleApplicationEventMulticaster();
    }


    public ApplicationEventMulticaster getApplicationEventMulticaster() {
        return applicationEventMulticaster;
    }

    @Override
    public void addApplicationListener(ApplicationListener<?> applicationListener) {
        Assert.checkNonNull(applicationListener, "applicationListener must not be null!");
        if (this.applicationEventMulticaster != null) {
            this.applicationEventMulticaster.addApplicationListener(applicationListener);
        }
        this.applicationListeners.add(applicationListener);
    }

    @Override
    public void publishedEvent(ApplicationEvent applicationEvent) {
        if (this.earlyApplicationEvent != null) {
            this.earlyApplicationEvent.add(applicationEvent);
        } else {
            getApplicationEventMulticaster().multicasterEvent(applicationEvent);
        }
    }
}
