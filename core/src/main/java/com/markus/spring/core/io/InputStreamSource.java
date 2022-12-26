package com.markus.spring.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author: markus
 * @date: 2022/12/26 10:09 PM
 * @Description: 获取输入流
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public interface InputStreamSource {
    InputStream getInputStream() throws IOException;
}
