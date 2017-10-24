package com.ahut.core.constants;

/**
 * Created by c2292 on 2017/10/23.
 */
public enum DemoInfoCode {

    SUCCESS("SUCCESS","查找成功"),
    FAIL("FAIL","数据不存在");
    ;

    DemoInfoCode(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    private String errCode;
    private String errMsg;

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
}
