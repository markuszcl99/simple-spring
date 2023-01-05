package com.markus.spring.beans.factory.support;

import com.markus.spring.beans.factory.config.BeanDefinition;
import com.markus.spring.beans.factory.config.BeanPostProcessor;
import com.markus.spring.beans.factory.config.ConfigurableBeanFactory;
import com.markus.spring.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.markus.spring.core.util.Assert;
import com.markus.spring.core.util.ClassUtils;
import com.sun.istack.internal.Nullable;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author: markus
 * @date: 2022/12/25 9:05 PM
 * @Description: BeanFactory抽象基类
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    /**
     * Map from bean name to merged RootBeanDefinitions
     */
    private final Map<String, RootBeanDefinition> mergedBeanDefinitions = new ConcurrentHashMap<>(256);

    /**
     * 指示是否已注册任何InstantiationAwareBeanPostProcessors
     */
    private volatile boolean hasInstantiationAwareBeanPostProcessors;

    /**
     * BeanPostProcessor 应用在Bean创建阶段
     */
    private final List<BeanPostProcessor> beanPostProcessors = new CopyOnWriteArrayList<>();

    @Nullable
    private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();


    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        Assert.notNull(beanPostProcessor, "beanPostProcessor must not be null!");
        // 删除旧的后置处理器
        this.beanPostProcessors.remove(beanPostProcessor);
        // 增加特殊BeanPostProcessor表示
        if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor) {
            this.hasInstantiationAwareBeanPostProcessors = true;
        }
        // 添加到尾部
        this.beanPostProcessors.add(beanPostProcessor);
    }

    @Override
    public void setBeanClassLoader(ClassLoader beanClassLoader) {
        this.beanClassLoader = beanClassLoader == null ? ClassUtils.getDefaultClassLoader() : beanClassLoader;
    }


    @Override
    public ClassLoader getBeanClassLoader() {
        return this.beanClassLoader;
    }

    //---------------------------------------------------------------------
    // Implementation of BeanFactory interface
    //---------------------------------------------------------------------
    @Override
    public boolean containsBean(String name) {
        return false;
    }

    @Override
    public Object getBean(String name) {
        return doGetBean(name, null, null);
    }

    @Override
    public Object getBean(String name, Object[] args) {
        return doGetBean(name, null, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) {
        return doGetBean(name, requiredType, null);
    }

    @SuppressWarnings("unchecked")
    protected <T> T doGetBean(final String name, @Nullable final Class<T> requiredType, @Nullable final Object[] args) {
        Object bean;
        Object sharedInstance = getSingleton(name);
        if (sharedInstance != null) {
            return (T) getObjectForBeanInstance(sharedInstance, name);
        }
        RootBeanDefinition mbd = getMergedLocalBeanDefinition(name);
        bean = createBean(name, mbd, args);
        return (T) getObjectForBeanInstance(bean, name);
    }

    private Object getObjectForBeanInstance(Object beanInstance, String beanName) {
        // todo 后续完善FactoryBean
        return beanInstance;
    }

    protected RootBeanDefinition getMergedLocalBeanDefinition(String beanName) {
        RootBeanDefinition mbd = this.mergedBeanDefinitions.get(beanName);
        // 短路优化
        if (mbd != null && !mbd.stale) {
            return mbd;
        }
        return getMergedBeanDefinition(beanName, getBeanDefinition(beanName));
    }

    private RootBeanDefinition getMergedBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        // todo Spring这里是递归调用 查找父容器是否存在可合并的BeanDefinition，最终将所有的GenericBeanDefinition合并到RootBeanDefinition
        //  我们这里先不实现这么复杂，就在当前容器创建一个RootBeanDefinition即可，将GenericBeanDefinition信息copy到RootBeanDefinition
        RootBeanDefinition mbd = new RootBeanDefinition(beanDefinition);
        return mbd;
    }

    protected boolean hasInstantiationAwareBeanPostProcessors() {
        return this.hasInstantiationAwareBeanPostProcessors;
    }

    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }

    //---------------------------------------------------------------------
    // Abstract methods to be implemented by subclasses
    //---------------------------------------------------------------------

    protected abstract BeanDefinition getBeanDefinition(String beanName);

    protected abstract Object createBean(String beanName, RootBeanDefinition beanDefinition, Object[] args);
}
