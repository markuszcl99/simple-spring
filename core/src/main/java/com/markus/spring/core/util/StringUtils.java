package com.markus.spring.core.util;

import cn.hutool.core.collection.CollectionUtil;
import com.sun.istack.internal.Nullable;

import java.util.Collection;

/**
 * @author: markus
 * @date: 2022/12/30 10:01 PM
 * @Description: 字符串处理工具
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public abstract class StringUtils {

    private static final String[] EMPTY_STRING_ARRAY = {};

    public static String[] toStringArray(@Nullable Collection<String> collection) {
        return CollectionUtil.isNotEmpty(collection) ? collection.toArray(EMPTY_STRING_ARRAY) : EMPTY_STRING_ARRAY;
    }
}
