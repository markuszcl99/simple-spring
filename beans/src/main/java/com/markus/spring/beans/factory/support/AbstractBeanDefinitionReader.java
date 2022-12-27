package com.markus.spring.beans.factory.support;

import com.markus.spring.beans.BeansException;
import com.markus.spring.core.io.DefaultResourceLoader;
import com.markus.spring.core.io.Resource;
import com.markus.spring.core.io.ResourceLoader;
import com.markus.spring.core.util.Assert;
import com.sun.istack.internal.Nullable;

import java.io.InputStream;

/**
 * @author: markus
 * @date: 2022/12/26 10:27 PM
 * @Description:
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    private BeanDefinitionRegistry registry;

    @Nullable
    private ResourceLoader resourceLoader;

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        Assert.notNull(registry, "BeanDefinitionRegistry must not be null");
        this.registry = registry;
        // todo Spring会判断registry类型，进而强转registry到ResourceLoader 或者 通过PathMatchingResourcePatternResolver返回ResourceLoader
        // 这里我们直接默认采用DefaultResourceLoader 不复杂设计了
        this.resourceLoader = new DefaultResourceLoader();
    }

    @Override
    public BeanDefinitionRegistry getBeanDefinitionRegistry() {
        return this.registry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return this.resourceLoader;
    }

    @Override
    public int loadBeanDefinitions(String location) {
        Assert.notNull(location, "location must not be null");
        ResourceLoader resourceLoader = getResourceLoader();
        if (resourceLoader == null) {
            throw new BeansException("Cannot load bean definitions from location [" + location + "]: no ResourceLoader available");
        }
        Resource resource = resourceLoader.getResource(location);
        return loadBeanDefinitions(resource);
    }

    @Override
    public int loadBeanDefinitions(String... locations) {
        Assert.notNull(locations, "location array must not be null");
        int count = 0;
        for (String location : locations) {
            count += loadBeanDefinitions(location);
        }
        return count;
    }

    @Override
    public int loadBeanDefinitions(Resource... resources) {
        Assert.notNull(resources, "resource array must not be null");
        int count = 0;
        for (Resource resource : resources) {
            count += loadBeanDefinitions(resources);
        }
        return count;
    }
}
