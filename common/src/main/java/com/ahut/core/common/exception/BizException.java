package com.ahut.core.common.exception;

import com.ahut.core.common.constant.CodeDesc;

/**
 * Created by c2292 on 2017/10/23.
 */
public class BizException extends RuntimeException{
    private static final long serialVersionUID = -767001296333121054L;

    private String errCode;
    private String errMsg;

    public BizException() {
    }

    public BizException(CodeDesc codeDesc) {
        super(codeDesc.desc());
        this.errCode = codeDesc.code();
        this.errMsg = codeDesc.desc();
    }

    public BizException(CodeDesc codeDesc,Throwable cause){
        this(codeDesc.code(),codeDesc.desc(),cause);
    }

    public BizException(String errCode, String errMsg) {
        super(errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public BizException(String errCode, String errMsg,Throwable cause) {
        super(errMsg + " : " + cause.getMessage(),cause);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public BizException(String errCode,String errMsg,String message){
        super(message);
        this.errMsg = errMsg;
        this.errCode = errCode;
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

    public BizException(String errCode,String errMsg,Throwable cause,boolean enableSuppression,boolean writableStackTrace){
        super(errMsg,cause,enableSuppression,writableStackTrace);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }
}
