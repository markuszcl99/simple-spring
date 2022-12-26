package com.markus.spring.beans.factory.support;

import com.markus.spring.core.io.Resource;
import com.markus.spring.core.io.ResourceLoader;

/**
 * @author: markus
 * @date: 2022/12/26 10:27 PM
 * @Description:
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{

    @Override
    public BeanDefinitionRegistry getBeanDefinitionRegistry() {
        return null;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return null;
    }

    @Override
    public int loadBeanDefinitions(String location) {
        return 0;
    }

    @Override
    public int loadBeanDefinitions(String... location) {
        return 0;
    }

    @Override
    public int loadBeanDefinitions(Resource... resource) {
        return 0;
    }
}
