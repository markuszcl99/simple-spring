package com.markus.spring.beans.factory.support;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.markus.spring.beans.factory.DisposableBean;
import com.markus.spring.beans.factory.ObjectFactory;
import com.markus.spring.beans.factory.config.SingletonBeanRegistry;
import com.markus.spring.core.util.Assert;
import com.markus.spring.core.util.StringUtils;

import java.util.*;
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
     * 销毁Bean实例缓存
     */
    private final Map<String, Object> disposableBeans = new LinkedHashMap<>();

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

    /**
     * 当前容器正在创建的bean名称集合
     */
    private final Set<String> singletonsCurrentlyInCreation = Collections.newSetFromMap(new ConcurrentHashMap<>(16));

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
                    if (objectFactory != null) {
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

    public boolean isSingletonCurrentlyInCreation(String beanName) {
        return this.singletonsCurrentlyInCreation.contains(beanName);
    }

    protected void addSingletonFactory(String beanName, ObjectFactory<?> singletonFactory) {
        Assert.notNull(singletonFactory, "singletonFactory must not be null!");
        synchronized (this.singletonObjects) {
            this.singletonFactories.put(beanName, singletonFactory);
            this.earlySingletonObjects.remove(beanName, singletonFactory);
        }
    }

    protected void registerDisposableBean(String beanName, DisposableBean disposableBean) {
        synchronized (this.disposableBeans) {
            this.disposableBeans.put(beanName, disposableBean);
        }
    }

    public void destroySingletons() {
        String[] disposableBeanNames;
        synchronized (this.disposableBeans) {
            disposableBeanNames = StringUtils.toStringArray(this.disposableBeans.keySet());
        }
        for (int i = 0; i < disposableBeanNames.length; i++) {
            destroySingleton(disposableBeanNames[i]);
        }
    }

    public void destroySingleton(String beanName) {
        removeSingleton(beanName);

        DisposableBean disposableBean;
        synchronized (this.disposableBeans) {
            disposableBean = (DisposableBean) this.disposableBeans.remove(beanName);
        }
        //todo 这里先实现单个无依赖的bean销毁，后续实现多依赖bean销毁
        disposableBean.destroy();
    }

    private void removeSingleton(String beanName) {
        this.singletonObjects.remove(beanName);
        this.earlySingletonObjects.remove(beanName);
        this.singletonFactories.remove(beanName);
    }
}
