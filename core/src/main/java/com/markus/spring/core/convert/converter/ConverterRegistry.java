package com.markus.spring.core.convert.converter;

/**
 * @author: markus
 * @date: 2023/1/8 3:46 PM
 * @Description: 转换器注册接口
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public interface ConverterRegistry {

    void addConverter(Converter<?, ?> converter);

    void addConverter(GenericConverter genericConverter);

    void addConverterFactory(ConverterFactory<?, ?> converterFactory);
}
