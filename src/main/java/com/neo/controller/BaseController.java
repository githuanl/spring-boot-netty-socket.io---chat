package com.neo.controller;

import com.alibaba.fastjson.JSON;
import com.neo.entity.BaseEntity;
import com.neo.entity.Result;
import com.neo.enums.EResultType;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class BaseController<T extends BaseEntity> {


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
