package com.neo.controller;

import com.neo.entity.BaseEntity;
import com.neo.entity.Result;
import com.neo.enums.EResultType;

public class BaseController<T extends BaseEntity> {

    public Result retResultData(EResultType type) {
        return new Result(type);
    }

    public Result retResultData(EResultType type, Object data) {
        return new Result(type, data);
    }

    public Result retResultData(Integer code, String message) {
        return new Result(code, message);
    }

    public Result retResultData(Integer code, String message, Object data) {
        return new Result(code, message, data);
    }





}
