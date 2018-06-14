package com.neo.entity;

import com.neo.enums.EResultType;

/**
 * Created by liudong on 2018/6/11.
 */
public class Result<T> {
    /*返回码*/
    private Integer code;
    /*返回信息提示*/
    private String message;
    /*返回的数据*/
    private T data;

    public Result() {
    }

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(EResultType type,T data) {
        this.code = type.getCode();
        this.message = type.getMsg();
        this.data = data;
    }

    public Result(EResultType type) {
        this.code = type.getCode();
        this.message = type.getMsg();
    }

    @Override
    public String toString() {
        return "Result [code=" + code + ", message=" + message + ", data=" + data + "]";
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
