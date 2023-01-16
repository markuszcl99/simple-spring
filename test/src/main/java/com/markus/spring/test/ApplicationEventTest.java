package com.markus.spring.test;

import com.markus.spring.context.ApplicationEvent;
import com.markus.spring.context.ApplicationListener;
import com.markus.spring.context.support.ClassPathXmlApplicationContext;

/**
 * @author: markus
 * @date: 2023/1/16 10:41 PM
 * @Description: 事件相关代码功能测试
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class ApplicationEventTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();

        context.addApplicationListener(new MyApplicationListener());
        context.publishedEvent(new MyApplicationEvent("Hello Application Event"));
    }

    static class MyApplicationEvent extends ApplicationEvent {

        /**
         * Constructs a prototypical Event.
         *
         * @param source The object on which the Event initially occurred.
         * @throws IllegalArgumentException if source is null.
         */
        public MyApplicationEvent(Object source) {
            super(source);
        }
    }

    static class MyApplicationListener implements ApplicationListener<MyApplicationEvent> {

        @Override
        public void onApplicationEvent(MyApplicationEvent event) {
            System.out.println("MyApplicationEvent 事件监听成功: " + event.getSource());
        }
    }
}
