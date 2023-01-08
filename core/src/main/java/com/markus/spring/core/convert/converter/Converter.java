package com.markus.spring.core.convert.converter;

/**
 * @author: markus
 * @date: 2023/1/8 3:45 PM
 * @Description: 类型转换器
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public interface Converter<S, T> {
    /**
     * 进行类型转换，将source转换为目标类型对象
     * @param source
     * @return
     */
    T convert(S source);
}
