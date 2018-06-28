package com.neo.exception;

import com.alibaba.fastjson.JSON;
import com.neo.entity.Result;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * Created by liudong on 2018/6/11.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static Logger logger = LogManager.getLogger(GlobalExceptionHandler.class.getName());

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public String errorHandler(HttpServletRequest req, Exception e) throws Exception {
        log(e, req);
        return JSON.toJSONString(new Result(-1, e.getMessage()));
    }

    @ExceptionHandler(value = ParameterException.class)
    @ResponseBody
    public String parameterHandler(Exception e) throws Exception {
        return JSON.toJSONString(new Result(-1, e.getMessage()));
    }

    //重复
    @ExceptionHandler(value = RepeatException.class)
    @ResponseBody
    public String repeatHandler(Exception e) throws Exception {
        return JSON.toJSONString(new Result(-1, e.getMessage()));
    }

    private void log(Exception ex, HttpServletRequest request) {
        logger.error("有异常啦：============》" + ex.getMessage());
        logger.error("************************异常开始*******************************");
//        if(getUser() != null)
//            logger.error("当前用户id是" + getUser().getUserId());
        logger.error(ex);
        logger.error("请求地址：" + request.getRequestURL());
        Enumeration enumeration = request.getParameterNames();
        logger.error("请求参数：");
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement().toString();
            logger.error(name + "---" + request.getParameter(name));
        }

        StackTraceElement[] error = ex.getStackTrace();
        for (StackTraceElement stackTraceElement : error) {
            logger.error(stackTraceElement.toString());
        }
        logger.error("************************异常结束*******************************");
    }

}