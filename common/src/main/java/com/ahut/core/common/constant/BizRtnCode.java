package com.ahut.core.common.constant;



/**
 * Created by c2292 on 2017/10/23.
 */
public enum BizRtnCode implements CodeDesc{
    SUCCESS("SUCCESS","服务调用成功"),
    FAIL("FAIL","服务不可用"),
    ERROR("FAIL","系统内部错误"),
    ;

    BizRtnCode(String code,String desc){
        this.code = code;
        this.desc = desc;
    }
    private String code;
    private String desc;

    @Override
    public String code() {
        return this.code;
    }

    @Override
    public String desc() {
        return this.desc;
    }
}
