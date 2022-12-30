package com.markus.spring.test;

import com.markus.spring.beans.factory.support.BeanDefinitionRegistry;
import com.markus.spring.beans.factory.support.DefaultListableBeanFactory;
import com.markus.spring.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @author: markus
 * @date: 2022/12/27 11:03 PM
 * @Description:
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class XmlBeanDefinitionReaderTest {
    public static void main(String[] args) {
        BeanDefinitionRegistry registry = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(registry);
        int count = reader.loadBeanDefinitions("classpath:/META-INF/application-context.xml");
        System.out.println("register BeanDefinition count : " + count);
    }
}
