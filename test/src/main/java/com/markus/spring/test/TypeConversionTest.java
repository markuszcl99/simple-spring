package com.markus.spring.test;

import com.markus.spring.beans.factory.support.DefaultListableBeanFactory;
import com.markus.spring.beans.factory.xml.XmlBeanDefinitionReader;
import com.markus.spring.core.convert.support.DefaultConversionService;
import com.markus.spring.core.convert.support.GenericConversionService;
import com.markus.spring.domain.User;

/**
 * @author: markus
 * @date: 2023/1/8 5:20 PM
 * @Description: 类型转换测试
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class TypeConversionTest {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.setConversionService(new DefaultConversionService());
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

        reader.loadBeanDefinitions("classpath:/META-INF/type-conversion.xml");

        User user = (User) beanFactory.getBean("user");
        System.out.println(user);
    }
}
