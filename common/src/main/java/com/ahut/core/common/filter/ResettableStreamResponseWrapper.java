package com.ahut.core.common.filter;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.*;

/**
 * Created by c2292 on 2017/10/23.
 */
public class ResettableStreamResponseWrapper extends HttpServletResponseWrapper{
    private ByteArrayOutputStream buffer = null;
    private ServletOutputStream out = null;
    private PrintWriter writer = null;

    public ResettableStreamResponseWrapper(HttpServletResponse response) throws IOException {
        super(response);
        buffer = new ByteArrayOutputStream();//真正存储数据的流
        out = new WappedOutputStream(buffer);
        writer = new PrintWriter(new OutputStreamWriter(buffer,this.getCharacterEncoding()));
    }
    /*重载父类获取outputstream的方法*/
    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return out;
    }
    /*重载父类获取writer的方法*/
    @Override
    public PrintWriter getWriter() throws IOException {
        return writer;
    }
    /*重载父类获取flushBuffer的方法*/
    @Override
    public void flushBuffer() throws IOException {
        if(out != null){
            out.flush();
        }
        if(writer != null){
            writer.flush();
        }
    }

    @Override
    public void reset() {
        buffer.reset();
    }
    /*将out.writer中的数据强制输出到WappedResponse的buffer里面 否则娶不到数据*/
    public byte[] getResponseData() throws IOException {
        flushBuffer();
        return buffer.toByteArray();
    }

    /*内部类 对servletOutputStream进行包装*/
    private class WappedOutputStream extends ServletOutputStream{
        private ByteArrayOutputStream bos = null;

        public WappedOutputStream(ByteArrayOutputStream stream) {
            bos = stream;
        }

        @Override
        public void write(byte[] b) throws IOException {
            bos.write(b,0,b.length);
        }

        @Override
        public boolean isReady() {
            return false;
        }

        @Override
        public void setWriteListener(WriteListener writeListener) {

        }

        @Override
        public void write(int b) throws IOException {
            bos.write(b);
        }
    }
}
