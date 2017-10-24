package com.ahut.core.common.packet;

import com.ahut.core.common.constant.BizRtnCode;
import com.ahut.core.common.constant.BizSubCode;
import com.ahut.core.common.constant.ErrCode;
import com.ahut.core.common.context.RequestContext;
import com.ahut.core.common.dto.BaseRequest;
import com.ahut.core.common.dto.BaseResponse;
import com.ahut.core.common.utils.JsonUtils;
import com.alibaba.druid.support.json.JSONUtils;
import com.sun.xml.internal.rngom.parse.host.Base;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by c2292 on 2017/10/23.
 */
public class Packet {
    private static Logger LOGGER = LoggerFactory.getLogger(Packet.class);
    static RequestContext requestContext = RequestContext.getCurrentContext();

    public static String setResponse(Object obj){
        return JsonUtils.toJsonString(obj);
    }

    public static void debugContext(){
        LOGGER.info("appId:{}",requestContext.get(BaseRequest.APPID));
        LOGGER.info("chnlType:{}",requestContext.get(BaseRequest.CHNLTYPE));
        LOGGER.info("areaCode:{}",requestContext.get(BaseRequest.AREACODE));
        LOGGER.info("deviceNo:{}",requestContext.get(BaseRequest.DEVICENO));
        LOGGER.info("merchantId:{}",requestContext.get(BaseRequest.MERCHANTID));
        LOGGER.info("msgTime:{}",requestContext.get(BaseRequest.MSGTIME));
        LOGGER.info("noncestr:{}",requestContext.get(BaseRequest.NONCESTR));
        LOGGER.info("openId:{}",requestContext.get(BaseRequest.OPENID));
        LOGGER.info("partnerAppId:{}",requestContext.get(BaseRequest.PARTNERAPPID));
        LOGGER.info("partnerId:{}",requestContext.get(BaseRequest.PARTNERID));
        LOGGER.info("sign:{}",requestContext.get(BaseRequest.SIGN));
        LOGGER.info("signType:{}",requestContext.get(BaseRequest.SIGNTYPE));
        LOGGER.info("traceCode:{}",requestContext.get(BaseRequest.TRANSCODE));
        LOGGER.info("txIp:{}",requestContext.get(BaseRequest.TXIP));
        LOGGER.info("version:{}",requestContext.get(BaseRequest.VERSION));
    }

    public static void setBizeResult(String errCode,String errMsg){
        if(ErrCode.SUCCESS.code().equals(errCode)){
            requestContext.set(BaseResponse.SUBCODE, BizSubCode.SUCCESS);
            requestContext.set(BaseResponse.ERRCODE,errCode);
            requestContext.set(BaseResponse.ERRMSG,errMsg);
        }else{
            requestContext.set(BaseResponse.SUBCODE,BizSubCode.FAIL);
            requestContext.set(BaseResponse.ERRCODE,errCode);
            requestContext.set(BaseResponse.ERRMSG,errMsg);
        }
    }

    public static void setBizSuccess(){
        requestContext.set(BaseResponse.SUBCODE,BizSubCode.SUCCESS);
        requestContext.set(BaseResponse.ERRCODE,ErrCode.SUCCESS.code());
        requestContext.set(BaseResponse.ERRMSG,ErrCode.SUCCESS.desc());
    }

    public static void setException(){
        requestContext.set(BaseResponse.EXCEPTION,"1");
    }

    public static void setBizException(){
        requestContext.set(BaseResponse.BIZEXCEPTION,"1");
    }

    public static boolean isException(){
        String ex = (String) requestContext.get(BaseResponse.EXCEPTION);
        if("1".equals(ex)){
            return true;
        }
        return false;
    }

    public static boolean isBizException(){
        String ex = (String) requestContext.get(BaseResponse.BIZEXCEPTION);
        if("1".equals(ex)){
            return true;
        }
        return false;
    }

    public static String getBizResult(){
        String subCode = requestContext.getString(BaseResponse.SUBCODE);
        String errCode = requestContext.getString(BaseResponse.ERRCODE);
        String errMsg = requestContext.getString(BaseResponse.ERRMSG);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setSubCode(subCode);
        baseResponse.setErrCode(errCode);
        baseResponse.setErrMsg(errMsg);

        return setResponse(baseResponse);
    }

    public static boolean isBizSuccess(BaseResponse baseResponse){
        if(BizRtnCode.SUCCESS.code().equals(baseResponse.getRtnCode())
        &&BizSubCode.SUCCESS.equals(baseResponse.getSubCode())){
            return true;
        }
        return false;
    }
}
