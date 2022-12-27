package com.markus.spring.beans.factory.xml;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author: markus
 * @date: 2022/12/27 10:29 PM
 * @Description: xml文档加载器
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public interface DocumentLoader {
    /**
     * 加载w3c Document，这里接口简化了，没有像Spring加入xml校验类型、解析实体等API
     * todo 后续可以优化
     * @param inputStream
     * @return
     */
    Document loadDocument(InputStream inputStream) throws Exception;
}
