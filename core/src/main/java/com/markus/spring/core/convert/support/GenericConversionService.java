package com.markus.spring.core.convert.support;

import com.markus.spring.core.convert.ConversionService;
import com.markus.spring.core.convert.converter.Converter;
import com.markus.spring.core.convert.converter.ConverterFactory;
import com.markus.spring.core.convert.converter.ConverterRegistry;
import com.markus.spring.core.convert.converter.GenericConverter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * @author: markus
 * @date: 2023/1/8 4:09 PM
 * @Description: 通用实现类型转换服务
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class GenericConversionService implements ConversionService, ConverterRegistry {

    private Map<GenericConverter.ConvertiblePair, GenericConverter> converters = new HashMap<>();


    @Override
    public boolean canConvert(Class<?> sourceType, Class<?> targetType) {
        GenericConverter converter = getConverter(sourceType, targetType);
        return converter != null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T convert(Object source, Class<?> targetType) {
        Class<?> sourceType = source.getClass();
        GenericConverter converter = getConverter(sourceType,targetType);
        return (T) converter.convert(source, sourceType, targetType);
    }

    @Override
    public void addConverter(Converter<?, ?> converter) {
        GenericConverter.ConvertiblePair convertiblePair = getRequiredTypeInfo(converter);
        ConverterAdapter converterAdapter = new ConverterAdapter(convertiblePair, converter);
        converters.put(convertiblePair, converterAdapter);
    }

    @Override
    public void addConverter(GenericConverter genericConverter) {
        for (GenericConverter.ConvertiblePair convertibleType : genericConverter.getConvertibleTypes()) {
            converters.put(convertibleType, genericConverter);
        }
    }

    @Override
    public void addConverterFactory(ConverterFactory<?, ?> converterFactory) {
        GenericConverter.ConvertiblePair convertiblePair = getRequiredTypeInfo(converterFactory);
        ConverterFactoryAdapter adapter = new ConverterFactoryAdapter(convertiblePair, converterFactory);
        converters.put(convertiblePair, adapter);
    }

    private GenericConverter.ConvertiblePair getRequiredTypeInfo(Object object) {
        Type[] types = object.getClass().getGenericInterfaces();
        ParameterizedType parameterizedType = (ParameterizedType) types[0];
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        Class sourceType = (Class) actualTypeArguments[0];
        Class targetType = (Class) actualTypeArguments[1];
        return new GenericConverter.ConvertiblePair(sourceType, targetType);
    }

    protected GenericConverter getConverter(Class<?> sourceType, Class<?> targetType) {
        List<Class<?>> sourceCandidates = getCandidateClass(sourceType);
        List<Class<?>> targetCandidates = getCandidateClass(targetType);
        for (Class<?> source : sourceCandidates) {
            for (Class<?> target : targetCandidates) {
                GenericConverter.ConvertiblePair convertiblePair = new GenericConverter.ConvertiblePair(source, target);
                GenericConverter converter = converters.get(convertiblePair);
                if (converter != null) {
                    return converter;
                }
            }
        }
        return null;
    }

    /**
     * 获取候选集类，给定一个类，返回一个类的继承结构下的所有的类
     *
     * @param clazz
     * @return
     */
    private List<Class<?>> getCandidateClass(Class<?> clazz) {
        List<Class<?>> candidates = new ArrayList<>();
        while (clazz != null) {
            candidates.add(clazz);
            clazz = clazz.getSuperclass();
        }
        return candidates;
    }

    private final class ConverterAdapter implements GenericConverter {

        private final ConvertiblePair typeInfo;
        private final Converter<Object, Object> converter;

        @SuppressWarnings("unchecked")
        public ConverterAdapter(ConvertiblePair typeInfo, Converter<?, ?> converter) {
            this.typeInfo = typeInfo;
            this.converter = (Converter<Object, Object>) converter;
        }

        @Override
        public Set<ConvertiblePair> getConvertibleTypes() {
            return Collections.singleton(typeInfo);
        }

        @Override
        public Object convert(Object source, Class<?> sourceType, Class<?> targetType) {
            return converter.convert(source);
        }
    }

    private final class ConverterFactoryAdapter implements GenericConverter {

        private final ConvertiblePair typeInfo;

        private final ConverterFactory<Object, Object> converterFactory;

        @SuppressWarnings("unchecked")
        public ConverterFactoryAdapter(ConvertiblePair typeInfo, ConverterFactory<?, ?> converterFactory) {
            this.typeInfo = typeInfo;
            this.converterFactory = (ConverterFactory<Object, Object>) converterFactory;
        }

        @Override
        public Set<ConvertiblePair> getConvertibleTypes() {
            return Collections.singleton(typeInfo);
        }

        @Override
        @SuppressWarnings("unchecked")
        public Object convert(Object source, Class<?> sourceType, Class<?> targetType) {
            return converterFactory.getConverter(targetType).convert(source);
        }
    }
}
