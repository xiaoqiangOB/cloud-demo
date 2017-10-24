package com.ahut.core.common.constant;

/**
 * Created by c2292 on 2017/10/23.
 */
public enum SysErrCode implements CodeDesc{
    E9999("999999","系统内部错误"),
    E0000("000000","服务调用成功"),
    E1000("100000","服务不可用"),
    E3001("300001","缺少必选参数"),
    E3002("300002","非法的参数"),
    ;
    private String code;
    private String desc;

    SysErrCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String code() {
        return this.code;
    }

    @Override
    public String desc() {
        return this.desc;
    }
}
