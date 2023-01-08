package com.markus.spring.core.util;

import com.sun.istack.internal.Nullable;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author: markus
 * @date: 2023/1/8 4:21 PM
 * @Description: Number工具类
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class NumberUtils {

    @SuppressWarnings("unchecked")
    public static <T extends Number> T parseNumber(String text, Class<T> targetClass) {
        Assert.notNull(text, "Text must not be null");
        Assert.notNull(targetClass, "Target class must not be null");

        String trimmedText = trimAllWhitespace(text);
        if (Byte.class == targetClass) {
            return (T) (isHexNumber(trimmedText) ? Byte.decode(trimmedText) : Byte.valueOf(trimmedText));
        } else if (Short.class == targetClass) {
            return (T) (isHexNumber(trimmedText) ? Short.decode(trimmedText) : Short.valueOf(trimmedText));
        } else if (Integer.class == targetClass) {
            return (T) (isHexNumber(trimmedText) ? Integer.decode(trimmedText) : Integer.valueOf(trimmedText));
        } else if (Long.class == targetClass) {
            return (T) (isHexNumber(trimmedText) ? Long.decode(trimmedText) : Long.valueOf(trimmedText));
        } else if (BigInteger.class == targetClass) {
            return (T) (isHexNumber(trimmedText) ? decodeBigInteger(trimmedText) : new BigInteger(trimmedText));
        } else if (Float.class == targetClass) {
            return (T) Float.valueOf(trimmedText);
        } else if (Double.class == targetClass) {
            return (T) Double.valueOf(trimmedText);
        } else if (BigDecimal.class == targetClass || Number.class == targetClass) {
            return (T) new BigDecimal(trimmedText);
        } else {
            throw new IllegalArgumentException(
                    "Cannot convert String [" + text + "] to target class [" + targetClass.getName() + "]");
        }
    }

    private static BigInteger decodeBigInteger(String value) {
        int radix = 10;
        int index = 0;
        boolean negative = false;

        // 处理负数 如果存在的话
        if (value.startsWith("-")) {
            negative = true;
            index++;
        }

        // 处理二进制说明符 如果存在的话
        if (value.startsWith("0x", index) || value.startsWith("0X", index)) {
            index += 2;
            radix = 16;
        } else if (value.startsWith("#", index)) {
            index++;
            radix = 16;
        } else if (value.startsWith("0", index) && value.length() > 1 + index) {
            index++;
            radix = 8;
        }

        BigInteger result = new BigInteger(value.substring(index), radix);
        return (negative ? result.negate() : result);
    }

    /**
     * 判断是否是十六进制字节串
     *
     * @param value
     * @return
     */
    private static boolean isHexNumber(String value) {
        int index = (value.startsWith("-") ? 1 : 0);
        return (value.startsWith("0x", index) || value.startsWith("0X", index) || value.startsWith("#", index));
    }

    public static String trimAllWhitespace(String text) {
        if (!hasLength(text)) {
            return text;
        }
        int len = text.length();
        StringBuilder sb = new StringBuilder(text.length());
        for (int i = 0; i < len; i++) {
            char c = text.charAt(i);
            if (!Character.isWhitespace(c)) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static boolean hasLength(@Nullable String text) {
        return text != null && !text.isEmpty();
    }
}
