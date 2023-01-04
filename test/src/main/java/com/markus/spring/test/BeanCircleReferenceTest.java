package com.markus.spring.test;

import com.markus.spring.beans.factory.support.DefaultListableBeanFactory;
import com.markus.spring.beans.factory.xml.XmlBeanDefinitionReader;
import com.markus.spring.domain.Teacher;

/**
 * @author: markus
 * @date: 2023/1/4 10:42 PM
 * @Description: Bean循环引用问题测试
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class BeanCircleReferenceTest {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        int beanCount = reader.loadBeanDefinitions("classpath:/META-INF/bean-circle-reference.xml");
        System.out.println("load bean count is " + beanCount);

        Teacher teacher = (Teacher) beanFactory.getBean("teacher");
        System.out.println(teacher);
    }
}
