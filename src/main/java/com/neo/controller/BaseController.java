package com.neo.controller;

import com.alibaba.fastjson.JSON;
import com.neo.entity.BaseEntity;
import com.neo.entity.Result;
import com.neo.entity.UserEntity;
import com.neo.enums.EResultType;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;

public class BaseController<T extends BaseEntity> {


    @Autowired
    HttpSession session;


    public UserEntity getSessionUser(){
        return (UserEntity) session.getAttribute("username");
    }

    protected Logger logger = LogManager.getLogger(getClass().getName());


    protected String retResultData(EResultType type) {
        return JSON.toJSONString(new Result(type));
    }

    protected String retResultData(EResultType type, Object data) {
        return JSON.toJSONString(new Result(type, data));
    }

    protected String retResultData(Integer code, String message) {
        return JSON.toJSONString(new Result(code, message));
    }

    protected String retResultData(Integer code, String message, Object data) {
        return JSON.toJSONString(new Result(code, message, data));
    }





}
