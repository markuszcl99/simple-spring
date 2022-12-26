package com.markus.spring.core.io;

import com.markus.spring.core.util.ResourceUtils;
import com.sun.istack.internal.Nullable;

/**
 * @author: markus
 * @date: 2022/12/26 10:20 PM
 * @Description: 资源加载器
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public interface ResourceLoader {

    String CLASSPATH_URL_PREFIX = ResourceUtils.CLASSPATH_URL_PREFIX;

    Resource getResource(String location);

    @Nullable
    ClassLoader getClassLoader();
}
