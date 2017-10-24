package com.ahut.core.common.filter;

import org.apache.commons.io.IOUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

/**
 * Created by c2292 on 2017/10/21.
 */
public class ResettableStreamRequestWrapper extends HttpServletRequestWrapper{
    private static final String DEFAULT_CHARACTER_ENCODING = "UTF-8";
    private byte[] bodyData;
    private HttpServletRequest request;
    private ResettableServletInputStream  resettableServletInputStream;

    public ResettableStreamRequestWrapper(HttpServletRequest request) {
        super(request);
        this.request = request;
        this.resettableServletInputStream = new ResettableServletInputStream();
    }

    public void resetInputStream(){
        if(this.bodyData != null){
            this.resettableServletInputStream.is = new ByteArrayInputStream(this.bodyData);
        }
    }

    public void setBodyData(byte[] bodyData) {
        this.bodyData = bodyData;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        if(this.bodyData == null){
            resolveBodyData();
        }
        return this.resettableServletInputStream;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        if(bodyData == null){
            resolveBodyData();
        }
        return new BufferedReader(new InputStreamReader(this.resettableServletInputStream));
    }

    private void resolveBodyData() throws IOException {
        this.bodyData = IOUtils.toByteArray(this.request.getReader(),getCharacterEncoding());
        this.resettableServletInputStream.is = new ByteArrayInputStream(this.bodyData);

    }

    public String getCharacterEncoding(){
        return super.getCharacterEncoding() != null ? super.getCharacterEncoding():DEFAULT_CHARACTER_ENCODING;
    }
    private class ResettableServletInputStream extends ServletInputStream{
        private InputStream is;

        @Override
        public boolean isFinished() {
            return false;
        }

        @Override
        public boolean isReady() {
            return false;
        }

        @Override
        public void setReadListener(ReadListener readListener) {

        }

        @Override
        public int read() throws IOException {
            return this.is.read();
        }
    }
}
