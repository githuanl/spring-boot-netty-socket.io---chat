package com.neo.controller;

import com.alibaba.fastjson.JSON;
import com.neo.dao.UserDao;
import com.neo.entity.MessageEntity;
import com.neo.entity.UserEntity;
import com.neo.serivce.ChatSerivice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/chat")
public class ChatController extends BaseController<MessageEntity> {

    @Autowired
    ChatSerivice chatSerivice;

    @ResponseBody
    @RequestMapping(value = "/t")
    public String test() {
        return "ks";
    }

    @ResponseBody
    @RequestMapping(value = "/t4")
    public String tests() {
        chatSerivice.sendApnData();
        return "ksss伙我不知";
    }


}
