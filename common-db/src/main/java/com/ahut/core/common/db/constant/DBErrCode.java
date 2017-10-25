package com.ahut.core.common.db.constant;



/**
 * Created by c2292 on 2017/10/24.
 */
public enum DBErrCode implements CodeDesc{
    DB000("DB000","数据库操作成功"),
    DB001("DB001","数据库连接失败"),
    DB002("DB002","查询数据库失败"),
    DB003("DB003","数据删除失败"),
    DB004("DB004","数据更新失败"),
    DB005("DB005","数据插入失败"),
    DB006("DB006","查询数据不存在"),
    DB007("DB007","获取指定序号失败"),
    DB008("DB008","该笔数据已存在"),
    ;

    private String code;
    private String desc;

    DBErrCode(String code, String desc) {
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
