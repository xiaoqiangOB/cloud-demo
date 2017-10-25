package com.ahut.core.common.db.exception;

import com.ahut.core.common.db.constant.CodeDesc;

/**
 * Created by c2292 on 2017/10/24.
 */
public class DBException extends RuntimeException{

    private static final long serialVersionUID = -2526034470506939656L;
    private String errCode;
    private String errMsg;

    public DBException() {
    }

    public DBException(CodeDesc codeDesc){
        super(codeDesc.desc());
        this.errCode = codeDesc.code();
        this.errMsg = codeDesc.desc();
    }

    public DBException(String errCode, String errMsg) {
       super(errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public DBException(CodeDesc codeDesc, Throwable cause){
        this(codeDesc.code(),codeDesc.code(),cause);
    }

    public DBException(String errCode, String errMsg,Throwable cause) {
        super(errMsg + " ：" +cause.getMessage(),cause);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public DBException(String errCode,String errMsg,String message) {
        super(message);
        this.errCode = errCode;
        this.errMsg = errMsg;
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
    public DBException(String errCode,String errMsg,Throwable cause,boolean enableSuppression,boolean writableStackTrace){
        super(errMsg,cause,enableSuppression,writableStackTrace);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }
}

