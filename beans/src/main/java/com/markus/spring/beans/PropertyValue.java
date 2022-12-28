package com.markus.spring.beans;

import com.markus.spring.core.util.Assert;

/**
 * @author: markus
 * @date: 2022/12/28 10:36 PM
 * @Description:
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class PropertyValue {

    private final String name;

    private final Object value;

    public PropertyValue(PropertyValue pv) {
        Assert.notNull(pv, "propertyValue must not be null");
        Assert.notNull(pv.name, "pv.name must not be null");
        Assert.notNull(pv.value, "pv.value must not be null");
        this.name = pv.name;
        this.value = pv.value;
    }

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public Object getValue() {
        return this.value;
    }
}
