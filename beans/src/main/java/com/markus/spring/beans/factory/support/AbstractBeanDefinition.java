package com.markus.spring.beans.factory.support;

import com.markus.spring.beans.MutablePropertyValues;
import com.markus.spring.beans.factory.config.BeanDefinition;
import com.sun.istack.internal.Nullable;

/**
 * @author: markus
 * @date: 2022/12/25 9:34 PM
 * @Description: 抽象BeanDefinition
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public abstract class AbstractBeanDefinition implements BeanDefinition {

    private static final String SCOPE_DEFAULT = "";

    private volatile Object beanClass;

    @Nullable
    private String scope = SCOPE_DEFAULT;

    @Nullable
    private Boolean lazyInit;

    private boolean primary = false;

    @Nullable
    private MutablePropertyValues propertyValues;

    public AbstractBeanDefinition() {
    }

    public AbstractBeanDefinition(BeanDefinition original) {
        setParentName(original.getParentName());
        setBeanClassName(original.getBeanClassName());
        setScope(original.getScope());
        setPrimary(original.isPrimary());
        setLazyInit(original.isLazyInit());

        if (original instanceof AbstractBeanDefinition) {
            AbstractBeanDefinition originalAbd = (AbstractBeanDefinition) original;
            setBeanClass(originalAbd.getBeanClass());
            if (originalAbd.hasPropertyValues()) {
                setPropertyValues(new MutablePropertyValues(originalAbd.getPropertyValues()));
            }
        } else {
            setPropertyValues(new MutablePropertyValues(original.getPropertyValues()));
        }
    }


    @Override
    public void setBeanClassName(@Nullable String beanClassName) {
        this.beanClass = beanClassName;
    }

    @Override
    public String getBeanClassName() {
        Object beanClassObject = this.beanClass;
        if (beanClassObject instanceof Class) {
            return ((Class<?>) beanClassObject).getName();
        } else {
            return (String) beanClassObject;
        }
    }

    public void setBeanClass(@Nullable Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    public Class<?> getBeanClass() {
        Object beanClassObject = this.beanClass;
        if (beanClassObject == null) {
            throw new IllegalStateException("No bean class specified on bean definition");
        }
        if (!(beanClassObject instanceof Class)) {
            throw new IllegalStateException(
                    "Bean class name [" + beanClassObject + "] has not been resolved into an actual Class");
        }
        return (Class<?>) beanClassObject;
    }

    @Override
    public void setScope(@Nullable String scope) {
        this.scope = scope;
    }

    @Override
    public String getScope() {
        return this.scope = scope;
    }

    @Override
    public void setLazyInit(boolean lazyInit) {
        this.lazyInit = lazyInit;
    }

    @Override
    public boolean isLazyInit() {
        return (this.lazyInit != null && this.lazyInit.booleanValue());
    }

    @Override
    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

    @Override
    public boolean isPrimary() {
        return this.primary;
    }

    @Override
    public boolean isSingleton() {
        return SCORE_SINGLETON.equals(scope) || SCOPE_DEFAULT.equals(this.scope);
    }

    @Override
    public boolean isPrototype() {
        return SCOPE_PROTOTYPE.equals(scope);
    }

    @Override
    public MutablePropertyValues getPropertyValues() {
        if (this.propertyValues == null) {
            this.propertyValues = new MutablePropertyValues();
        }
        return this.propertyValues;
    }

    @Override
    public boolean hasPropertyValues() {
        return (this.propertyValues != null && !this.propertyValues.isEmpty());
    }

    public void setPropertyValues(MutablePropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

}
