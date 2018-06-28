package com.neo.serivce;

import com.neo.entity.BaseEntity;
import com.neo.entity.GroupEntity;
import com.neo.entity.GroupUser;
import com.neo.entity.UserEntity;

import java.util.List;

/**
 * Created by liudong on 2018/6/8.
 */

public interface GroupSerivice<T extends BaseEntity> extends BaseSerivice<GroupEntity> {


    /**
     * 创建群组
     *
     * @param name
     * @return
     */
    GroupEntity creatGroup(String name, String avatar, UserEntity userEntity);


    /**
     * 根据 名字 查询对应的群
     *
     * @return
     */
    List<GroupEntity> findGroupsByGroupName(String groupName);


    /**
     * 获取 我所在的 所有的群
     *
     * @return
     */
    List<GroupEntity> findMyGroupsByUserId(String id);


    /**
     * 加入群组
     * @param entity
     * @param groupId
     * @return
     */
    GroupUser joinGroup(UserEntity entity, String groupId);


    /**
     * 获取 群下面的所有成员
     *
     * @return
     */
    List<GroupUser> findUsersByGroupId(String group_id);


}
