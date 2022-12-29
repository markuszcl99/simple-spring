package com.markus.spring.beans.factory.xml;

import com.markus.spring.core.io.Resource;

/**
 * @author: markus
 * @date: 2022/12/28 11:32 PM
 * @Description: Xml读取器上下文
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class XmlReaderContext {
    private final XmlBeanDefinitionReader reader;

    private final Resource resource;

    public XmlReaderContext(XmlBeanDefinitionReader reader, Resource resource) {
        this.reader = reader;
        this.resource = resource;
    }

    public XmlBeanDefinitionReader getReader() {
        return reader;
    }

    public Resource getResource() {
        return resource;
    }
}
