package com.ahut.core.common.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by c2292 on 2017/10/21.
 */
public class RequestContext extends ConcurrentHashMap<String,Object>{
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestContext.class);
    private static final long serialVersionUID = 9175115698971022173L;

    private static RequestContext requestContext = null;
    protected static Class<? extends RequestContext> contextClass = RequestContext.class;
    protected static final ThreadLocal<? extends RequestContext> threadLocal = new ThreadLocal<RequestContext>(){
        protected RequestContext initialValue(){
            try {
                return (RequestContext) RequestContext.contextClass.newInstance();
            } catch (Throwable var2) {
               throw new RuntimeException(var2);
            }
        }
    };

    public RequestContext(){
    }

    public static RequestContext getCurrentContext(){
        if(requestContext == null){
            requestContext = (RequestContext) threadLocal.get();
        }
        return requestContext;
    }

    public boolean getBoolean(String key){
        return this.getBoolean(key,false);
    }
    public boolean getBoolean(String key,boolean defaultResponse){
        Boolean b = (Boolean) this.get(key);
        return b != null ? b.booleanValue() : defaultResponse;
    }
    public void set(String key){
        this.put(key,Boolean.TRUE);
    }
    public void set(String key,Object value){
        if(value != null){
            this.put(key,value);
        }else{
            this.remove(key);
        }
    }
    public String getString(String key){
        Object obj = get(key);
        if(obj == null){
            return null;
        }
        return obj.toString();
    }

    public HttpServletRequest getRequest(){
        return (HttpServletRequest) this.get("request");
    }
    public void setRequest(HttpServletRequest request){
        this.put("request",request);
    }
    public HttpServletResponse getResponse(){
        return (HttpServletResponse) this.get("response");
    }
    public void setResponse(HttpServletResponse response){
        this.set("response",response);
    }

}
