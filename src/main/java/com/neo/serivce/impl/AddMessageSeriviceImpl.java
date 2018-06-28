package com.neo.serivce.impl;

import com.alibaba.fastjson.JSONObject;
import com.neo.dao.AddMessageDao;
import com.neo.dao.GroupDao;
import com.neo.dao.UserDao;
import com.neo.entity.*;
import com.neo.serivce.AddMessageSerivice;
import com.neo.utils.DateUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liudong on 2018/6/8.
 */
@Component
public class AddMessageSeriviceImpl<T extends BaseEntity> extends BaseSeriviceImpl<UserEntity> implements AddMessageSerivice<UserEntity> {

    @Resource
    UserDao userDao;

    @Resource
    AddMessageDao addMessageDao;

    @Resource
    GroupDao groupDao;

    /**
     * 添加好友、群组信息请求
     *
     * @param msg
     */
    @Override
    public void saveAddMessage(AddMessage msg) {
        msg.setTime(DateUtils.getDataTimeYMDHMS());
        userDao.saveEntity(msg);
    }

    /**
     * @description 查询消息盒子信息
     */
    @Override
    public JSONObject findAddInfo(String userId) {

        JSONObject obj = new JSONObject();

        List<AddMessage> list = addMessageDao.findAddInfo(userId);

        List<AddInfo> infos = new ArrayList<>();
        for (AddMessage message : list) {
            AddInfo info = new AddInfo();
            if (message.getType().equals("group")) {
                GroupEntity entity = (GroupEntity) groupDao.findEntityById(message.getGroupId());
                info.setContent("申请加入 '" + entity.getGroupname() + "' 群聊!");
            } else {
                info.setContent("申请添加你为好友");
            }

            info.setId(message.getId());
            info.setType(message.getType());
            info.setFrom(message.getFromUid());
            info.setUid(message.getToUid());
            info.setRead(message.getMsgResult().toString());
            UserEntity userEntity = getEntityById(message.getFromUid());
            userEntity.setPassword("");
            userEntity.setAuth_token("");
            info.setUser(userEntity);
            info.setFrom_group(message.getGroupId());
            info.setTime(message.getTime());
            info.setRemark(message.getRemark());
            infos.add(info);
        }

        obj.put("list", infos);
        obj.put("pages", 1);
        return obj;
    }



}
