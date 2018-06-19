package com.neo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.neo.entity.UserEntity;
import com.neo.enums.EResultType;
import com.neo.serivce.UserSerivice;
import com.neo.utils.SessionUtil;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController<UserEntity> {

    @Autowired
    private UserSerivice userSerivice;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String login(String name, String password, HttpSession session) {

        if (StringUtil.isNullOrEmpty(name) || StringUtil.isNullOrEmpty(password)) {
            return retResultData(-1, "用户名或密码不能为空");
        }

        UserEntity user = userSerivice.findUserByUserName(name);
        if (user != null) {
            if (!user.getPassword().equals(password)) {
                return retResultData(EResultType.PASSWORK_ERROR);
            }
            user.setPassword("");
            session.setAttribute("userName", JSON.toJSONString(user));
        } else {
            return retResultData(EResultType.PASSWORK_ERROR);
        }
        return retResultData(EResultType.SUCCESS, user);
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "login";
    }


    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public String register(String name, String password) {

        if (StringUtil.isNullOrEmpty(name) || StringUtil.isNullOrEmpty(password)) {
            return retResultData(-1, "用户名或密码不能为空");
        }

        UserEntity user = userSerivice.findUserByUserName(name);
        if (user != null) {
            return retResultData(-1, "用户名已存在");
        }

        user = userSerivice.register(name, password);

        return retResultData(EResultType.SUCCESS, user);
    }

    /**
     * 查询所有用户
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/findAllUser")
    public String findAllUser() {

        JSONObject obj = new JSONObject();
        obj.put("allUser", userSerivice.selectAll());
        obj.put("onLineUsers", SessionUtil.user_socket_Map.keySet());

        return retResultData(EResultType.SUCCESS, obj);
    }

}
