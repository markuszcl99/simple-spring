package com.markus.spring.beans.factory.xml;

import org.w3c.dom.Document;

/**
 * @author: markus
 * @date: 2022/12/28 11:30 PM
 * @Description: BeanDefinition Document读取器
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public interface BeanDefinitionDocumentReader {
    void registerBeanDefinitions(Document document, XmlReaderContext readerContext);
}
