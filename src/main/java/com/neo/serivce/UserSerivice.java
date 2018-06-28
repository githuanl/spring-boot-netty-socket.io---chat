package com.neo.serivce;

import com.neo.entity.BaseEntity;
import com.neo.entity.GroupEntity;
import com.neo.entity.GroupUser;
import com.neo.entity.UserEntity;

import java.util.List;

/**
 * Created by liudong on 2018/6/8.
 */

public interface UserSerivice<T extends BaseEntity> extends BaseSerivice<UserEntity> {

    UserEntity findUserByUserName(String name);

    /**
     * 使用 token 查询 实体
     *
     * @param access_token
     * @return
     */
    UserEntity findUserByToken(String access_token);

    /**
     * 注册
     *
     * @param name
     * @param password
     * @return
     */
    UserEntity register(String name, String password, String avatar);


    /**
     * 创建群组
     *
     * @param name
     * @return
     */
    GroupEntity creatGroup(String name, String avatar, UserEntity userEntity);


    /**
     * 更新 添加消息数据
     * @param entity
     * @param messageId
     * @return
     */
    void updateAddMessage(UserEntity entity,String groupId,String messageId);


    /**
     * 拒绝添加群组，或者，好友
     * @param messageBoxId
     */
    void updateAddMessage(String messageBoxId);

    /**
     * 加入群组
     * @param entity
     * @param groupId
     * @return
     */
    GroupUser joinGroup(UserEntity entity,String groupId);


    /**
     * 获取 我所在的 所有的群
     *
     * @return
     */
    List<GroupEntity> findMyGroupsByUserId(String id);

    /**
     * 获取 群下面的所有成员
     *
     * @return
     */
    List<GroupUser> findUsersByGroupId(String group_id);


    /**
     * 根据群的名字查询所有的群
     *
     * @return
     */
    List<GroupEntity> findGroupsByGroupName(String groupName);



}
