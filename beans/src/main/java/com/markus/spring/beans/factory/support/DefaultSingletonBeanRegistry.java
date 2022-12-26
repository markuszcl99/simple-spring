package com.markus.spring.beans.factory.support;

import com.markus.spring.beans.factory.ObjectFactory;
import com.markus.spring.beans.factory.config.SingletonBeanRegistry;
import com.markus.spring.core.util.Assert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: markus
 * @date: 2022/12/25 9:57 PM
 * @Description:
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    /**
     * 一级缓存 存放普通对象
     */
    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);

    /**
     * 二级缓存 存放提前暴露的对象（未完全初始化的）
     */
    private final Map<String, Object> earlySingletonObjects = new ConcurrentHashMap<>(16);

    /**
     * 三级缓存 存放代理
     */
    private final Map<String, ObjectFactory<?>> singletonFactories = new ConcurrentHashMap<>(16);

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        Assert.notNull(beanName, "Bean name must not be null");
        Assert.notNull(singletonObject, "Singleton object must not be null");
        synchronized (singletonObjects) {
            Object oldObject = this.singletonObjects.get(beanName);
            if (oldObject != null) {
                throw new IllegalStateException("Could not register object [" + singletonObject +
                        "] under bean name '" + beanName + "': there is already object [" + oldObject + "] bound");
            }
            addSingleton(beanName, singletonObject);
        }
    }

    private void addSingleton(String beanName, Object singletonObject) {
        synchronized (singletonObjects) {
            this.singletonObjects.put(beanName, singletonObject);
            this.earlySingletonObjects.remove(beanName);
            this.singletonFactories.remove(beanName);
        }
    }

    @Override
    public Object getSingleton(String beanName) {
        return getSingleton(beanName, true);
    }

    protected Object getSingleton(String beanName, boolean allowEarlyReference) {
        Object singletonObject = this.singletonObjects.get(beanName);
        if (singletonObject == null) {
            synchronized (this.singletonObjects) {
                singletonObject = this.earlySingletonObjects.get(beanName);
                if (singletonObject == null && allowEarlyReference) {
                    ObjectFactory<?> objectFactory = this.singletonFactories.get(beanName);
                    if (objectFactory == null) {
                        singletonObject = objectFactory.getObject();
                        this.earlySingletonObjects.put(beanName, singletonObject);
                        this.singletonObjects.remove(beanName);
                    }
                }
            }
        }
        return singletonObject;
    }

    @Override
    public boolean containSingleton(String beanName) {
        return false;
    }
}
