package com.markus.spring.test;

import com.markus.spring.beans.factory.support.DefaultListableBeanFactory;
import com.markus.spring.beans.factory.xml.XmlBeanDefinitionReader;
import com.markus.spring.domain.User;

/**
 * @author: markus
 * @date: 2023/1/6 11:20 PM
 * @Description:
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class DisposableBeanTest {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

        reader.loadBeanDefinitions("classpath:/META-INF/user-destroy-method.xml");

        User user = (User) beanFactory.getBean("user");
        System.out.println(user);

        // 模拟容器关闭，这里我们手动调用一下
        beanFactory.destroySingletons();
    }
}
