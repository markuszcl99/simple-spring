package com.markus.spring.core.convert.converter;

import java.util.Objects;
import java.util.Set;

/**
 * @author: markus
 * @date: 2023/1/8 3:47 PM
 * @Description: 通用转换器
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public interface GenericConverter {

    /**
     * 存储可转换的类型对
     *
     * @return
     */
    Set<ConvertiblePair> getConvertibleTypes();

    /**
     * 将源对象转换为目标类型的对象
     *
     * @param source
     * @param sourceType
     * @param targetType
     * @return
     */
    Object convert(Object source, Class<?> sourceType, Class<?> targetType);

    final class ConvertiblePair {
        private final Class<?> sourceType;
        private final Class<?> targetType;

        public ConvertiblePair(Class<?> sourceType, Class<?> targetType) {
            this.sourceType = sourceType;
            this.targetType = targetType;
        }

        public Class<?> getSourceType() {
            return sourceType;
        }

        public Class<?> getTargetType() {
            return targetType;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ConvertiblePair that = (ConvertiblePair) o;
            return this.sourceType.equals(that.sourceType) && this.targetType.equals(that.targetType);
        }

        @Override
        public int hashCode() {
            return this.sourceType.hashCode() * 31 + this.targetType.hashCode();
        }
    }
}
