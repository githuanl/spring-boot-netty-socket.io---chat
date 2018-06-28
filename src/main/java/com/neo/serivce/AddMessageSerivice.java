package com.neo.serivce;

import com.alibaba.fastjson.JSONObject;
import com.neo.entity.AddMessage;
import com.neo.entity.BaseEntity;
import com.neo.entity.UserEntity;

/**
 * Created by liudong on 2018/6/8.
 */

public interface AddMessageSerivice<T extends BaseEntity> extends BaseSerivice<UserEntity> {

    /**
     * 添加好友、群组信息请求
     * @param msg
     */
    void saveAddMessage(AddMessage msg);

    /**
     * @description 查询消息盒子信息
     */
    JSONObject findAddInfo(String userId);

}
