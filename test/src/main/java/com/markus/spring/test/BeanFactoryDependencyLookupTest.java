package com.markus.spring.test;

import com.markus.spring.beans.factory.BeanFactory;
import com.markus.spring.beans.factory.support.DefaultListableBeanFactory;
import com.markus.spring.beans.factory.xml.XmlBeanDefinitionReader;
import com.markus.spring.domain.User;
import com.markus.spring.domain.UserHolder;

/**
 * @author: markus
 * @date: 2022/12/31 10:35 PM
 * @Description: IoC容器依赖查找测试
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class BeanFactoryDependencyLookupTest {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:/META-INF/application-context.xml");

        User user = (User) beanFactory.getBean("user");
        System.out.println(user);

        UserHolder userHolder = (UserHolder) beanFactory.getBean("userHolder");
        System.out.println(userHolder);
    }
}
