package com.neo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.neo.entity.GroupEntity;
import com.neo.entity.GroupUser;
import com.neo.entity.UserEntity;
import com.neo.enums.EResultType;
import com.neo.serivce.UserSerivice;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController<UserEntity> {

    @Value("${server.port}")
    private Integer port;

    @Autowired
    private UserSerivice userSerivice;

    /**
     * 登录
     * @param name
     * @param password
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/login")
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
            session.setAttribute("username", user);
        } else {
            return retResultData(EResultType.PASSWORK_ERROR);
        }
        return retResultData(EResultType.SUCCESS, user);
    }

    // 注册
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public String register(String name, String password, String avatar) {

        if (StringUtil.isNullOrEmpty(name) || StringUtil.isNullOrEmpty(password)) {
            return retResultData(-1, "用户名或密码不能为空");
        }

        UserEntity user = userSerivice.findUserByUserName(name);
        if (user != null) {
            return retResultData(-1, "用户名已存在");
        }

        user = userSerivice.register(name, password, avatar);

        return retResultData(EResultType.SUCCESS, user);
    }

    //退出登录
    @RequestMapping(value = "/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "login";
    }

    //获取当前登录人的Token
    @ResponseBody
    @RequestMapping(value = "/getToken")
    public String getAuthToken(HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("username");
        return retResultData(EResultType.SUCCESS, user.getAuth_token());
    }


    /**
     * 目前是 查询系统中的 所有人员
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/findAllUser")
    public String findAllUser(HttpSession session) {

        JSONObject obj = new JSONObject();
        obj.put("mine", session.getAttribute("username"));

        //分组
        JSONArray array = new JSONArray();
        JSONObject f = new JSONObject();
        f.put("groupname", "我的好友");
        f.put("id", "0");
        f.put("list", userSerivice.selectAll());
        array.add(f);
        obj.put("friend", array);

        //获取所有的群组
        UserEntity userEntity = (UserEntity) session.getAttribute("username");
        obj.put("group", userSerivice.findMyGroupsByUserId(userEntity.getId()));

        return retResultData(0, "", obj);
    }


    /**
     * 加群
     * @return
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/addGroup")
    public String addGroup() {
        return retResultData(0, "");
    }


    /**
     * 根据群的名字 查询 对应的群，群名称为空则查询 所有群
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/findGroupsByName")
    public String findGroupsByName(String page,String name) {

        List<GroupEntity> list = userSerivice.findGroupsByGroupName(name);
        return retResultData(0, "", list);
    }



    /**
     * 查询指定群下面的 群成员
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/findGroupUsers")
    public String findGroupUsers(String id) {

        List<GroupUser> list = userSerivice.findUsersByGroupId(id);
        for (GroupUser user : list) {
            user.setId(user.getUser_id());
        }
        JSONObject obj = new JSONObject();
        obj.put("list", list);

        return retResultData(0, "", obj);
    }



    @Value("${web.upload-path}")
    private String path;        //文件上传的路径

    //文件上传
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody
    String uploadImg(@RequestParam("file") MultipartFile file,
                     HttpServletRequest request) throws UnknownHostException {

        if(file.isEmpty()){
            return retResultData(-1, "上传文件不能为空");
        }

//        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();

        byte[] bytes = new byte[0];
        try {
            bytes = file.getBytes();
            Path ph = Paths.get(path + uuid + fileName);
            Files.write(ph, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        InetAddress addr = InetAddress.getLocalHost();
        String ip = addr.getHostAddress();//获得本机IP
        JSONObject obj = new JSONObject();
        obj.put("src", "http://" + ip + ":" + port + "/static/" + uuid + fileName);
        //返回json
        return retResultData(0, "", obj);
    }

}
