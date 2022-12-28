package com.markus.spring.beans.factory.xml;

import com.sun.istack.internal.Nullable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @author: markus
 * @date: 2022/12/28 11:34 PM
 * @Description:
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class DefaultBeanDefinitionDocumentReader implements BeanDefinitionDocumentReader {

    @Nullable
    private XmlReaderContext readerContext;

    @Override
    public void registerBeanDefinitions(Document document, XmlReaderContext readerContext) {
        this.readerContext = readerContext;
        doRegisterBeanDefinition(document.getDocumentElement());
    }

    public void doRegisterBeanDefinition(Element root) {
        // todo 真正的注册逻辑
    }
}
