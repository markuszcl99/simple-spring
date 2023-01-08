package com.markus.spring.core.convert.support;

import cn.hutool.core.util.NumberUtil;
import com.markus.spring.core.convert.converter.Converter;
import com.markus.spring.core.convert.converter.ConverterFactory;
import com.markus.spring.core.util.NumberUtils;
import com.sun.istack.internal.Nullable;

/**
 * @author: markus
 * @date: 2023/1/8 4:17 PM
 * @Description: String --> Number的类型转换工厂
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class StringToNumberConverterFactory implements ConverterFactory<String, Number> {
    @Override
    public <T extends Number> Converter<String, T> getConverter(Class<T> targetType) {
        return null;
    }

    private static final class StringToNumber<T extends Number> implements Converter<String, T> {

        private final Class<T> targetType;

        public StringToNumber(Class<T> targetType) {
            this.targetType = targetType;
        }

        @Override
        @Nullable
        public T convert(String source) {
            if (source.isEmpty()) {
                return null;
            }
            return NumberUtils.parseNumber(source, this.targetType);
        }
    }
}
