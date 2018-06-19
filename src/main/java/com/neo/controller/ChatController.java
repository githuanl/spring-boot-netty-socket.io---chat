package com.neo.controller;

import com.neo.entity.MessageEntity;
import com.neo.enums.EResultType;
import com.neo.serivce.ChatSerivice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "/chat")
public class ChatController extends BaseController<MessageEntity> {

    @Autowired
    ChatSerivice chatSerivice;

    @ResponseBody
    @RequestMapping(value = "/t")
    public String test() {
        return retResultData(EResultType.SUCCESS, "ks");
    }

    @ResponseBody
    @RequestMapping(value = "/t4")
    public String tests() {
        chatSerivice.sendApnData();
        return retResultData(EResultType.SUCCESS);
    }


}
