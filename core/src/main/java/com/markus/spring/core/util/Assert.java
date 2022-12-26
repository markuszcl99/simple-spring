package com.markus.spring.core.util;

import com.sun.istack.internal.Nullable;

/**
 * @author: markus
 * @date: 2022/12/25 9:59 PM
 * @Description:
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public abstract class Assert {
    public static void notNull(@Nullable Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
