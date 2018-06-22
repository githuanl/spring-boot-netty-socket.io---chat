package com.neo.dao;

import com.neo.entity.BaseEntity;
import com.neo.entity.GroupEntity;
import com.neo.entity.GroupUser;
import com.neo.entity.UserEntity;

import java.util.List;

/**
 * Created by summer on 2017/5/5.
 */
public interface UserDao<T extends BaseEntity> extends BaseDao<UserEntity> {

    //根据名称查询 user
    UserEntity findUserByUserName(String name);

    //根据Token查询 user
    UserEntity findUserByToken(String access_token);

    /**
     * 创建群组
     *
     * @param name
     * @return
     */
    GroupEntity creatGroup(String name, String avatar);

    /**
     * 获取 我所在的 所有的群
     *
     * @return
     */
    List<GroupEntity> findMyGroupsByUserId(String userId);

    /**
     * 获取 群 下面的所有人员
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
