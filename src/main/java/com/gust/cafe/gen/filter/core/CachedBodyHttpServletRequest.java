package com.gust.cafe.gen.filter.core;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

/**
 * 缓存请求体,解决`如果在filter中读取了request.getInputStream()，那么controller中就无法获取到request.getInputStream()`的问题
 * <p>
 * 原理:
 * (1) 原本的流程是`HttpServletRequest`在filter>>controller中只能读取一次`request.getInputStream()`。
 * (2) `HttpServletRequestWrapper`是一个继承了`HttpServletRequest`的抽象类,可以通过继承`HttpServletRequestWrapper`来实现对`HttpServletRequest`的包装。
 * (3) 包装类`CachedBodyHttpServletRequest`继承了`HttpServletRequestWrapper`,并重写了`getInputStream`和`getReader`方法,使得`HttpServletRequestWrapper`可以多次读取请求体。
 * (4) 上一步提到的"可以多次读取",本质就是我们在包装类里已经读了第一次存了起来,因此后续可以多次读取。
 * (5) 为了确保我们这个包装类是所有的filter中第一个执行的,我们需要配合一个filter中设置`@Order(1)`,确保它是第一个执行的。
 * (6) 第四步提到的具体操作是:
 * 6.1. 通过`request.getInputStream()`拿到请求体的输入流。
 * 6.2. 通过`BufferedReader`逐行读取请求体,并将其转换为字符串。
 * 6.3. 最后再转换为字节数组进行缓存(即这里设计的`cachedBody`)。
 * (7) 提供一个方法糖：`getRequestBodyString`方法,它的作用是直接获取请求体字符串,即JSON字符串,可以在filter中直接使用。
 * 比如我们在任何一个(order>1)的filter中,在重写`doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)`方法时,
 * 可以通过`((CachedBodyHttpServletRequest) request).getRequestBodyString()`将对应的当前线程的 request 强转为我们设计的包装类,然后调用`getRequestBodyString`方法获取请求体字符串。
 * 然后可以在filter对此字符串进行处理,比如解析JSON,然后根据JSON中的某个字段进行判断是否需要进行防抖等操作。
 * 并且filter处理完去到controller时,controller中还是可以正常填充`@RequestBody`注解的对象的。或者通过`request.getInputStream()`获取到请求体的输入流。
 * </p>
 */
public class CachedBodyHttpServletRequest extends HttpServletRequestWrapper {

    private byte[] cachedBody;

    public CachedBodyHttpServletRequest(HttpServletRequest request) throws IOException {
        super(request);
        // 缓存请求体
        InputStream requestInputStream = request.getInputStream();
        this.cachedBody = getRequestBody(requestInputStream).getBytes("UTF-8");
    }

    private String getRequestBody(InputStream inputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        return new CachedBodyServletInputStream(this.cachedBody);
    }

    @Override
    public BufferedReader getReader() throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.cachedBody);
        return new BufferedReader(new InputStreamReader(byteArrayInputStream));
    }

    // 提供一个API可以直接获取请求体字符串,即JSON字符串,可以在filter中直接使用
    public String getRequestBodyString() throws IOException {
        InputStream inputStream = new CachedBodyServletInputStream(this.cachedBody);
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
        return stringBuilder.toString();
    }

    private static class CachedBodyServletInputStream extends ServletInputStream {

        private final ByteArrayInputStream byteArrayInputStream;

        public CachedBodyServletInputStream(byte[] cachedBody) {
            this.byteArrayInputStream = new ByteArrayInputStream(cachedBody);
        }

        @Override
        public boolean isFinished() {
            return byteArrayInputStream.available() == 0;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setReadListener(ReadListener readListener) {
            // Not implemented
        }

        @Override
        public int read() throws IOException {
            return byteArrayInputStream.read();
        }
    }
}