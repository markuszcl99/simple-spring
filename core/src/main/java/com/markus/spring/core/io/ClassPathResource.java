package com.markus.spring.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author: markus
 * @date: 2022/12/26 10:11 PM
 * @Description:
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class ClassPathResource extends AbstractFileResolvingResource{
    @Override
    public InputStream getInputStream() throws IOException {
        // todo 实现InputStream构建
        return null;
    }
}
