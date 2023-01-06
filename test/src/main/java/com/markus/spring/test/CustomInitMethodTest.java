package com.markus.spring.test;

import com.markus.spring.beans.factory.support.DefaultListableBeanFactory;
import com.markus.spring.beans.factory.xml.XmlBeanDefinitionReader;
import com.markus.spring.domain.User;

/**
 * @author: markus
 * @date: 2023/1/6 10:29 PM
 * @Description: 用户自定义方法 Spring回调功能实现
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class CustomInitMethodTest {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:/META-INF/user-custom-method.xml");

        User user = (User) beanFactory.getBean("user");
        System.out.println(user);
    }
}
