package com.neo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

/**
 * Created by liudong on 2018/6/15.
 */
@Controller
public class IndexController extends BaseController {

    @RequestMapping("/login")
    public String index(HashMap<String, Object> map) {
        map.put("hello", "hello");
        return "/index";
    }
}
