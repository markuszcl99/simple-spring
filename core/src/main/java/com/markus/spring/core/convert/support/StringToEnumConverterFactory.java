package com.markus.spring.core.convert.support;

import com.markus.spring.core.convert.converter.Converter;
import com.markus.spring.core.convert.converter.ConverterFactory;

/**
 * @author: markus
 * @date: 2023/1/8 5:51 PM
 * @Description:
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class StringToEnumConverterFactory implements ConverterFactory<String, Enum> {
    @Override
    public <T extends Enum> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToEnum<>(targetType);
    }

    private static class StringToEnum<T extends Enum> implements Converter<String, T> {

        private final Class<T> enumType;

        public StringToEnum(Class<T> targetType) {
            this.enumType = targetType;
        }

        @Override
        public T convert(String source) {
            if (source.isEmpty()) {
                return null;
            }
            return (T) Enum.valueOf(enumType, source.trim());
        }
    }
}
