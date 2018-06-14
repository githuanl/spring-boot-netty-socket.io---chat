package com.neo.exception;

import com.neo.entity.Result;
import com.neo.enums.EResultType;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
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
    public Result<String> errorHandler(HttpServletRequest req, Exception e) throws Exception {
        System.out.println("有异常啦：===》" + e.getMessage());
        log(e, req);
        Result result = new Result(EResultType.GLOABLE_ERROR.getCode(), EResultType.GLOABLE_ERROR.getMsg());
        return result;
    }

    @ExceptionHandler(value = ParameterException.class)
    @ResponseBody
    public Result<String> parameterHandler() throws Exception {
        Result result = new Result(EResultType.PARAMETER_ERROR.getCode(), EResultType.PARAMETER_ERROR.getMsg());
        return result;
    }


    private void log(Exception ex, HttpServletRequest request) {
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