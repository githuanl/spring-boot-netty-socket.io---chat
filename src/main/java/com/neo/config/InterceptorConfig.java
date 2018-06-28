package com.neo.config;

/**
 * Created by liudong on 2018/6/8.
 */

import com.alibaba.fastjson.JSON;
import com.neo.entity.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;


public class InterceptorConfig implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(InterceptorConfig.class);

    /**
     * 进入controller层之前拦截请求
     *
     * @param request
     * @param response
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

//        log.info("---------------------开始进入请求地址拦截----------------------------");
        HttpSession session = request.getSession();
        if (!StringUtils.isEmpty(session.getAttribute("username"))) {
            return true;
        } else {
            // 跳转登录
//            String url = "/login";
//            response.sendRedirect(url);
            PrintWriter printWriter = response.getWriter();
            printWriter.write(JSON.toJSONString(new Result(-1, "please login")));
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
//        log.info("--------------处理请求完成后视图渲染之前的处理操作---------------");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
//        log.info("---------------视图渲染之后的操作-------------------------0");
    }
}