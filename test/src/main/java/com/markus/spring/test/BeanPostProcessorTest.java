package com.markus.spring.test;

import com.markus.spring.beans.factory.support.DefaultListableBeanFactory;
import com.markus.spring.beans.factory.xml.XmlBeanDefinitionReader;
import com.markus.spring.domain.User;
import com.markus.spring.feature.UserBeanPostProcessor;

/**
 * @author: markus
 * @date: 2023/1/5 11:06 PM
 * @Description:
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class BeanPostProcessorTest {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:/META-INF/application-context.xml");
        beanFactory.addBeanPostProcessor(new UserBeanPostProcessor());
        User user = (User) beanFactory.getBean("user");
        System.out.println(user);
    }
}
