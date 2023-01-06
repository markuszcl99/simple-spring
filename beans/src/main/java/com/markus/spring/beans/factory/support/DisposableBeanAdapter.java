package com.markus.spring.beans.factory.support;

import cn.hutool.core.util.StrUtil;
import com.markus.spring.beans.BeansException;
import com.markus.spring.beans.factory.DisposableBean;

import java.lang.reflect.Method;

/**
 * @author: markus
 * @date: 2023/1/6 10:48 PM
 * @Description:
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
class DisposableBeanAdapter implements DisposableBean {

    private final String beanName;
    private final Object bean;
    private final String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, RootBeanDefinition mbd) {
        this.beanName = beanName;
        this.bean = bean;
        this.destroyMethodName = mbd.getDestroyMethodName();
    }

    @Override
    public void destroy() {
        // todo Spring这里还有销毁的BeanPostProcessor后处理器回调，我们这里暂不实现
        // 暂时实现在用户角度经常使用的两种销毁前回调方法
        if (bean instanceof DisposableBean) {
            ((DisposableBean) bean).destroy();
        }

        if (StrUtil.isNotEmpty(this.destroyMethodName)) {
            try {
                Method method = bean.getClass().getMethod(destroyMethodName);
                method.invoke(bean);
            } catch (Exception e) {
                throw new BeansException("user custom destroy method invoke fail!");
            }
        }
    }
}
