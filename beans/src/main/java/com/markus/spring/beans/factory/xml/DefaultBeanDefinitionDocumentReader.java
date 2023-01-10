package com.markus.spring.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import com.markus.spring.beans.BeansException;
import com.markus.spring.beans.MutablePropertyValues;
import com.markus.spring.beans.PropertyValue;
import com.markus.spring.beans.factory.config.BeanReference;
import com.markus.spring.beans.factory.support.AbstractBeanDefinition;
import com.markus.spring.beans.factory.support.BeanDefinitionRegistry;
import com.markus.spring.beans.factory.support.GenericBeanDefinition;
import com.markus.spring.core.util.StringUtils;
import com.sun.istack.internal.Nullable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author: markus
 * @date: 2022/12/28 11:34 PM
 * @Description: xml文档解析
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class DefaultBeanDefinitionDocumentReader implements BeanDefinitionDocumentReader {

    @Nullable
    private XmlReaderContext readerContext;

    @Override
    public void registerBeanDefinitions(Document document, XmlReaderContext readerContext) {
        this.readerContext = readerContext;
        try {
            doRegisterBeanDefinition(document.getDocumentElement());
        } catch (ClassNotFoundException e) {
            throw new BeansException("register BeanDefinition Error", e);
        }
    }

    public void doRegisterBeanDefinition(Element root) throws ClassNotFoundException {
        // todo Spring 采用BeanDefinitionParserDelegate解析对应的标签，这里就先简单实现对应xml标签提取构建BeanDefinition了，待IoC整体链路打通后再打算完善
        NodeList nl = root.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (node instanceof Element) {
                Element element = (Element) node;
                processBeanDefinition(element);
            }
        }

    }

    /**
     * 该函数要做的事情本来应该是BeanDefinitionParserDelegate来做的，这里简化一下，直接reader解析了
     *
     * @param element
     */
    private void processBeanDefinition(Element element) throws ClassNotFoundException {
        if (!element.getNodeName().equals("bean")) {
            // 如果不是bean标签 直接return
            return;
        }
        String id = element.getAttribute("id");
        String name = element.getAttribute("name");
        String className = element.getAttribute("class");
        String scope = element.getAttribute("scope");
        String initMethodName = element.getAttribute("init-method");
        String destroyMethodName = element.getAttribute("destroy-method");

        if (StrUtil.isEmpty(className)) {
            throw new BeansException("xml bean element must have class attribute!");
        }
        Class<?> beanClass = Class.forName(className);
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition(beanClass);
        beanDefinition.setScope(scope);

        if (StrUtil.isNotEmpty(initMethodName)) {
            beanDefinition.setInitMethodName(initMethodName);
        }
        if (StrUtil.isNotEmpty(destroyMethodName)) {
            beanDefinition.setDestroyMethodName(destroyMethodName);
        }

        String beanName = StrUtil.isNotEmpty(id) ? id : name;
        if (StrUtil.isEmpty(beanName)) {
            beanName = StrUtil.lowerFirst(beanClass.getSimpleName());
        }
        // 开始解析 <property /> 标签
        NodeList nl = element.getChildNodes();
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (node instanceof Element) {
                Element subElement = (Element) node;
                String attrName = subElement.getAttribute("name");
                String attrValue = subElement.getAttribute("value");
                String attrRef = subElement.getAttribute("ref");

                Object value = StrUtil.isNotEmpty(attrRef) ? new BeanReference(attrRef) : attrValue;

                propertyValues.addPropertyValue(new PropertyValue(attrName, value));
            }
        }
        beanDefinition.setPropertyValues(propertyValues);

        BeanDefinitionRegistry registry = this.readerContext.getReader().getBeanDefinitionRegistry();
        if (!registry.containsBeanDefinition(beanName)) {
            registry.registerBeanDefinition(beanName, beanDefinition);
        }

    }

}
