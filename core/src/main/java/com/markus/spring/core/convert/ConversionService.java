package com.markus.spring.core.convert;

/**
 * @author: markus
 * @date: 2023/1/8 3:41 PM
 * @Description: 类型转换类
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public interface ConversionService {

    /**
     * 能否进行类型转换
     *
     * @param sourceType
     * @param targetType
     * @return
     */
    boolean canConvert(Class<?> sourceType, Class<?> targetType);

    /**
     * 进行类型转换
     * @param source
     * @param targetType
     * @param <T>
     * @return
     */
    <T> T convert(Object source, Class<?> targetType);
}
