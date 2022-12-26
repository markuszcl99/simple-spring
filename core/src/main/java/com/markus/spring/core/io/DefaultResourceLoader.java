package com.markus.spring.core.io;

import com.markus.spring.core.util.Assert;
import com.markus.spring.core.util.ClassUtils;
import com.sun.istack.internal.Nullable;

/**
 * @author: markus
 * @date: 2022/12/26 10:21 PM
 * @Description: 资源加载器默认实现
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class DefaultResourceLoader implements ResourceLoader {

    @Nullable
    private ClassLoader classLoader;

    public DefaultResourceLoader() {
        this.classLoader = ClassUtils.getDefaultClassLoader();
    }

    public DefaultResourceLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public Resource getResource(String location) {
        Assert.notNull(location, "location must not be null!");
        if (location.startsWith(CLASSPATH_URL_PREFIX)) {
            return new ClassPathResource();
        }
        // todo 其他资源加载器待实现
        return null;
    }

    @Override
    @Nullable
    public ClassLoader getClassLoader() {
        return this.classLoader != null ? this.classLoader : ClassUtils.getDefaultClassLoader();
    }
}
