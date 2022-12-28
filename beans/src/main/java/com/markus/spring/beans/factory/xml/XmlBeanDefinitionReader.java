package com.markus.spring.beans.factory.xml;

import com.markus.spring.beans.BeanUtils;
import com.markus.spring.beans.BeansException;
import com.markus.spring.beans.factory.support.AbstractBeanDefinitionReader;
import com.markus.spring.beans.factory.support.BeanDefinitionRegistry;
import com.markus.spring.core.io.Resource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author: markus
 * @date: 2022/12/26 10:28 PM
 * @Description:
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    private DocumentLoader documentLoader = new DefaultDocumentLoader();

    private Class<? extends BeanDefinitionDocumentReader> documentReaderClass =
            DefaultBeanDefinitionDocumentReader.class;

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    public int loadBeanDefinitions(Resource resource) {
        try (InputStream is = resource.getInputStream()) {
            return doLoadBeanDefinitions(is, resource);
        } catch (IOException ex) {
            throw new BeansException("IOException parsing XML document from " + resource.getDescription(), ex);
        }
    }

    protected int doLoadBeanDefinitions(InputStream inputStream, Resource resource) {
        try {
            // 第一步 加载Document
            Document doc = doLoadDocument(inputStream);
            // 第二步 注册BeanDefinition
            int count = registerBeanDefinitions(doc, resource);
            return count;
        } catch (ParserConfigurationException ex) {
            throw new BeansException("Parser configuration exception parsing XML from " + resource.getDescription(), ex);
        } catch (IOException ex) {
            throw new BeansException("IOException parsing XML document from " + resource.getDescription(), ex);
        } catch (Throwable ex) {
            throw new BeansException("Unexpected exception parsing XML document from " + resource, ex);
        }
    }

    private Document doLoadDocument(InputStream inputStream) throws Exception {
        return documentLoader.loadDocument(inputStream);
    }

    private int registerBeanDefinitions(Document doc, Resource resource) {
        BeanDefinitionDocumentReader documentReader = createBeanDefinitionDocumentReader();
        int countBefore = getBeanDefinitionRegistry().getBeanDefinitionCount();
        documentReader.registerBeanDefinitions(doc, new XmlReaderContext(this, resource));
        return getBeanDefinitionRegistry().getBeanDefinitionCount() - countBefore;
    }

    protected BeanDefinitionDocumentReader createBeanDefinitionDocumentReader() {
        return BeanUtils.instantiateClass(this.documentReaderClass);
    }
}
