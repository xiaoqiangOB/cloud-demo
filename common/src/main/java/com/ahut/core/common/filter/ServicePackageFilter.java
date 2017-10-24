package com.ahut.core.common.filter;


import com.ahut.core.common.constant.BizRtnCode;
import com.ahut.core.common.constant.Constans;
import com.ahut.core.common.context.RequestContext;
import com.ahut.core.common.dto.BaseRequest;
import com.ahut.core.common.dto.BaseResponse;
import com.ahut.core.common.packet.Packet;
import com.google.common.base.Strings;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StreamUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;


/**
 * Created by c2292 on 2017/10/21.
 */
//@Order(1)
//@WebFilter(filterName = "/packetFilter",urlPatterns = "/*")
public class ServicePackageFilter implements Filter{
    private static Logger LOGGER = LoggerFactory.getLogger(ServicePackageFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ResettableStreamRequestWrapper resettableStreamRequestWrapper = new ResettableStreamRequestWrapper((HttpServletRequest) servletRequest);
        String url = resettableStreamRequestWrapper.getRequestURI();
        LOGGER.info("交易【" + url + "】 开始执行");
        String traceNo = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader(Constans.GLOBAL_TRACE_NO);
        LOGGER.info("TraceNo:{}",traceNo);

        String requestBody = null;
        InputStream inputStream = resettableStreamRequestWrapper.getInputStream();
        requestBody = StreamUtils.copyToString(inputStream, Charset.forName("UTF-8"));
        LOGGER.info("Reqeust Body:{}",requestBody);
        Gson gson = new Gson();
        BaseRequest baseRequest = gson.fromJson(requestBody, BaseRequest.class);
        LOGGER.info(baseRequest.toString());
        RequestContext requestContext = RequestContext.getCurrentContext();
        requestContext.set(BaseRequest.APPID,baseRequest.getAppId());
        requestContext.set(BaseRequest.AREACODE,baseRequest.getAreaCode());
        requestContext.set(BaseRequest.BIZCONTENT,baseRequest.getBizContent());
        requestContext.set(BaseRequest.CHNLTYPE,baseRequest.getChnlType());
        requestContext.set(BaseRequest.DEVICENO,baseRequest.getDeviceNo());
        requestContext.set(BaseRequest.MERCHANTID,baseRequest.getMerchangId());
        requestContext.set(BaseRequest.MSGTIME,baseRequest.getMsgTime());
        requestContext.set(BaseRequest.NONCESTR,baseRequest.getNonceStr());
        requestContext.set(BaseRequest.OPENID,baseRequest.getOpenId());
        requestContext.set(BaseRequest.PARTNERAPPID,baseRequest.getPartnerAppId());
        requestContext.set(BaseRequest.PARTNERID,baseRequest.getPartnerId());
        requestContext.set(BaseRequest.SIGN,baseRequest.getSign());
        requestContext.set(BaseRequest.SIGNTYPE,baseRequest.getSignType());
        requestContext.set(BaseRequest.TRANSCODE,baseRequest.getTransCode());
        requestContext.set(BaseRequest.TXIP,baseRequest.getTxIp());
        requestContext.set(Constans.GLOBAL_TRACE_NO,traceNo);

        if(baseRequest.getBizContent() != null){
            resettableStreamRequestWrapper.setBodyData(baseRequest.getBizContent().getBytes());
        }
        resettableStreamRequestWrapper.resetInputStream();
        ResettableStreamResponseWrapper resettableStreamResponseWrapper = new ResettableStreamResponseWrapper((HttpServletResponse) servletResponse);
        filterChain.doFilter(resettableStreamRequestWrapper,resettableStreamResponseWrapper);
        String result = new String(resettableStreamResponseWrapper.getResponseData());
        String responseBody = result;
        LOGGER.info("Result:{}",result);
        if(Packet.isBizException()){
            BaseResponse baseResponse = new BaseResponse();
            if(!Strings.isNullOrEmpty(result)){
                BaseResponse baseResponse1 = gson.fromJson(result, BaseResponse.class);
                BeanUtils.copyProperties(baseResponse1,baseResponse);
            }
            baseResponse.setRtnCode(BizRtnCode.SUCCESS.code());
            baseResponse.setRtnMsg(BizRtnCode.SUCCESS.desc());
            responseBody = Packet.setResponse(baseResponse);
            LOGGER.error("Biz exception!!!");
        }else if(Packet.isException()){
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setRtnCode(BizRtnCode.ERROR.code());
            baseResponse.setRtnMsg(BizRtnCode.ERROR.desc());
            responseBody = Packet.setResponse(baseResponse);
            LOGGER.error("Exception");
        }else{
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setRtnCode(BizRtnCode.SUCCESS.code());
            baseResponse.setRtnMsg(BizRtnCode.SUCCESS.desc());
            baseResponse.setBizcontent(result);
            baseResponse.setSubCode(requestContext.getString(BaseResponse.SUBCODE));
            baseResponse.setErrCode(requestContext.getString(BaseResponse.ERRCODE));
            baseResponse.setErrMsg(requestContext.getString(BaseResponse.ERRMSG));
            responseBody = Packet.setResponse(baseResponse);
        }
        LOGGER.info("Response Body:{}",responseBody);
        servletResponse.setContentLength(responseBody.getBytes().length);
        servletResponse.getOutputStream().write(responseBody.getBytes());
        requestContext.clear();
        LOGGER.info("交易【" + url + "】执行结束");
    }
}
