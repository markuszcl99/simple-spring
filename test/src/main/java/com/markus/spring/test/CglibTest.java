package com.markus.spring.test;

import com.markus.spring.domain.User;
import com.markus.spring.domain.UserHolder;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.Method;

/**
 * @author: markus
 * @date: 2023/1/10 11:56 PM
 * @Description:
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class CglibTest {
    public static void main(String[] args) {
//        Enhancer enhancer = new Enhancer();
//        enhancer.setSuperclass(UserHolder.class);
//        enhancer.setCallbackFilter(new CallbackFilter() {
//            @Override
//            public int accept(Method method) {
//                if (method.getName().equals("getUser")) {
//                    return 0;
//                }
//                return 1;
//            }
//        });
//        enhancer.setCallback(new Callback() {
//            @Override
//            public int hashCode() {
//                return super.hashCode();
//            }
//        });
//        enhancer.
    }
}
