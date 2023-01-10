package com.markus.spring.beans.factory.support;

import com.markus.spring.beans.factory.BeanFactory;
import com.sun.istack.internal.Nullable;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

/**
 * @author: markus
 * @date: 2023/1/10 11:04 PM
 * @Description: 基于cglib方式实现对象的创建
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class CglibSubclassingInstantiationStrategy extends SimpleInstantiationStrategy {
    @Override
    protected Object instantiateWithMethodInjection(RootBeanDefinition bd, @Nullable String beanName, BeanFactory owner, Constructor<?> ctor, Object... args) {
        return new CglibSubclassCreator(bd, owner).instantiate(ctor, args);
    }

    private static class CglibSubclassCreator {
        private final RootBeanDefinition beanDefinition;
        private final BeanFactory owner;

        public CglibSubclassCreator(RootBeanDefinition beanDefinition, BeanFactory owner) {
            this.beanDefinition = beanDefinition;
            this.owner = owner;
        }

        public Object instantiate(@Nullable Constructor<?> ctor, Object... args) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(this.beanDefinition.getBeanClass());
            enhancer.setCallback(new NoOp() {
                @Override
                public int hashCode() {
                    return super.hashCode();
                }
            });
            if (ctor == null) {
                return enhancer.create();
            } else {
                return enhancer.create(ctor.getParameterTypes(), args);
            }
        }
    }
}
