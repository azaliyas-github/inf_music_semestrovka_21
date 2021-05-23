package ru.itis.utils;

import org.springframework.web.servlet.mvc.method.annotation.*;

import java.io.*;

public abstract class StreamUtils {
    public static StreamingResponseBody returnBytes(byte[] bytes) {
        return outputStream -> {
            var byteStream = new ByteArrayOutputStream();
            byteStream.write(bytes);
            byteStream.writeTo(outputStream);
        };
    }
}
