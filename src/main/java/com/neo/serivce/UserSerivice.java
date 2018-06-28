package com.neo.serivce;

import com.neo.entity.BaseEntity;
import com.neo.entity.UserEntity;

import java.util.List;

/**
 * Created by liudong on 2018/6/8.
 */

public interface UserSerivice<T extends BaseEntity> extends BaseSerivice<UserEntity> {


    /**
     * 根据名称查具体的人
     *
     * @param name
     * @return
     */
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
     * 根据用户名查询 对应的人员
     *
     * @return
     */
    List<UserEntity> findUsersByName(String page, String name);
}
