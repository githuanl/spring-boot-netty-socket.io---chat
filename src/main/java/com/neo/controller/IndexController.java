package com.neo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by liudong on 2018/6/15.
 */
@Controller
public class IndexController extends BaseController {

    @RequestMapping("/")
    public String index(HttpSession session) {
        if (session.getAttribute("username") != null) {
            return "static/index.html";
        }
        return "static/login.html";
    }
}
