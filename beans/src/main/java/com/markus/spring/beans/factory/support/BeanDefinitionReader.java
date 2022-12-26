package com.markus.spring.beans.factory.support;

import com.markus.spring.core.io.Resource;
import com.markus.spring.core.io.ResourceLoader;

/**
 * @author: markus
 * @date: 2022/12/26 10:17 PM
 * @Description: BeanDefinition 读取器接口定义
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getBeanDefinitionRegistry();

    ResourceLoader getResourceLoader();

    int loadBeanDefinitions(String location);

    int loadBeanDefinitions(String... location);

    int loadBeanDefinitions(Resource resource);

    int loadBeanDefinitions(Resource... resource);
}
