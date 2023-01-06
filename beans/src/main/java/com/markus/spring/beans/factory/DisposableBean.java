package com.markus.spring.beans.factory;

/**
 * @author: markus
 * @date: 2023/1/6 10:47 PM
 * @Description: Bean销毁回调方法
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public interface DisposableBean {
    void destroy();
}
