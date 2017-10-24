package com.ahut.core.common.constant;

/**
 * Created by c2292 on 2017/10/23.
 */
public enum ErrCode implements CodeDesc{
    SUCCESS("000000","交易成功"),
    E000000("000000","交易成功"),

    E009999("E009999","系统内部错误"),

    /*数据库操作*/
    E009001("E009001","数据库单条插入失败"),
    E009002("E009002","数据库批量插入失败"),
    E009003("E009003","数据库更新失败"),
    E009004("E009004","创建序列失败"),
    E009005("E009005","删除序列失败"),
    E009006("E009006","查询序列失败"),
    E009007("E009007","统计交易失败"),
    E009008("E009008","查询失败"),
    ;

   private String code;
   private String desc;



    ErrCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String desc() {
        return desc;
    }
}
