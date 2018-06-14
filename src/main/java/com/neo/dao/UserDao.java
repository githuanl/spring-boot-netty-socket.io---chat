package com.neo.dao;

import com.neo.entity.BaseEntity;
import com.neo.entity.UserEntity;

/**
 * Created by summer on 2017/5/5.
 */
public interface UserDao<T extends BaseEntity> extends BaseDao<UserEntity>  {

    //根据名称查询 user
    public UserEntity findUserByUserName(String name);

    //根据Token查询 user
    public UserEntity findUserByToken(String access_token);

}
