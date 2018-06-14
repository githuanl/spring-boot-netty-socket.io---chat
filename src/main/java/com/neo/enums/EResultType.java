package com.neo.enums;

/**
 * Created by liudong on 2018/6/11.
 */
public enum EResultType {

    /**
     * 访问成功返回
     */
    SUCCESS(1, "success"),

    /**
     * 用户名或者密码错误
     */
    PASSWORK_ERROR(-1, "用户名或者密码错误"),

    /**
     * 数据不存在返回
     */
    NOT_FOUND(-1, "notFound [数据不存在 或者 数据为空]"),

    /**
     * 异常返回
     */
    ERROR(-1, "error [未知异常]"),

    /**
     * 参数有异常返回
     */
    GLOABLE_ERROR(-1, "全局异常"),

    /**
     * 参数有异常返回
     */
    PARAMETER_ERROR(-1, "parameter error [参数异常:参数为空或者参数类型不符]");

    private Integer code;

    private String msg;

    private EResultType(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
