package com.markus.spring.core.convert.converter;

/**
 * @author: markus
 * @date: 2023/1/8 4:03 PM
 * @Description: 转换器工厂
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public interface ConverterFactory<S, R> {

    /**
     * 获取转换器
     * @param targetType
     * @param <T>
     * @return
     */
    <T extends R> Converter<S, T> getConverter(Class<T> targetType);
}
