package com.markus.spring.core.convert.support;

import com.markus.spring.core.convert.converter.ConverterRegistry;

/**
 * @author: markus
 * @date: 2023/1/8 4:08 PM
 * @Description: 通用转换服务默认实现
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class DefaultConversionService extends GenericConversionService {
    public DefaultConversionService() {
        addDefaultConverters(this);
    }

    public static void addDefaultConverters(ConverterRegistry converterRegistry) {
        // 添加各类类型转换工厂
        converterRegistry.addConverterFactory(new StringToNumberConverterFactory());
        converterRegistry.addConverterFactory(new StringToEnumConverterFactory());
    }
}
