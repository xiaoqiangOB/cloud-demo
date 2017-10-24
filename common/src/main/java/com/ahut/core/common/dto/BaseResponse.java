package com.ahut.core.common.dto;

import com.ahut.core.common.packet.Packet;
import com.ahut.core.common.utils.JsonUtils;

import java.io.Serializable;

/**
 * Created by c2292 on 2017/10/23.
 */
public class BaseResponse<T> implements Serializable{
    private static final long serialVersionUID = -7688720693599620047L;

    public static final String RTNCODE = "rtnCode";
    public static final String RTNMSG = "rtnMsg";
    public static final String SUBCODE = "subCode";
    public static final String ERRCODE = "errCode";
    public static final String ERRMSG = "errMsg";
    public static final String NONCESTR = "nonceStr";
    public static final String SING = "sign";
    public static final String BIZCONTENT = "bizContent";
    public static final String BIZEXCEPTION = "bizException";
    public static final String EXCEPTION = "Exception";

    /*返回状态码，只是标识通讯层面的成功与失败，不代表业务是否成功 SUCCESS/FAIL*/
    private String rtnCode;
    /*返回状态描述*/
    private String rtnMsg;
    /*业务返回结果 SUCCESS/FAIL*/
    private String subCode;
    /*错误代码*/
    private String errCode;
    /*错误描述*/
    private String errMsg;
    /*s随机字符串 主要保证签名不可预测*/
    private String nonceStr;
    /*签名*/
    private String sign;
    /*业务应答内容*/
    private String bizContent;

    public String getRtnCode() {
        return rtnCode;
    }

    public void setRtnCode(String rtnCode) {
        this.rtnCode = rtnCode;
    }

    public String getRtnMsg() {
        return rtnMsg;
    }

    public void setRtnMsg(String rtnMsg) {
        this.rtnMsg = rtnMsg;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getBizContent() {
        return bizContent;
    }

    public void setBizContent(String bizContent) {
        this.bizContent = bizContent;
    }

    public void setBizcontent(T bizcontent){
        String content = Packet.setResponse(bizcontent);
        this.bizContent = content;
    }

    public T getBizContent(Class<T> clazz){
        return JsonUtils.strToJson(this.bizContent,clazz);
    }
    @Override
    public String toString() {
        return "BaseResponse{" +
                "rtnCode='" + rtnCode + '\'' +
                ", rtnMsg='" + rtnMsg + '\'' +
                ", subCode='" + subCode + '\'' +
                ", errCode='" + errCode + '\'' +
                ", errMsg='" + errMsg + '\'' +
                ", nonceStr='" + nonceStr + '\'' +
                ", sign='" + sign + '\'' +
                ", bizContent='" + bizContent + '\'' +
                '}';
    }
}
