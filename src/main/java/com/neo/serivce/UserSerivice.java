package com.neo.serivce;

import com.neo.entity.BaseEntity;
import com.neo.entity.UserEntity;
import org.springframework.stereotype.Component;

/**
 * Created by liudong on 2018/6/8.
 */

public interface UserSerivice<T extends BaseEntity> extends BaseSerivice<UserEntity> {

    public UserEntity findUserByUserName(String name);

    /**
     * 使用 token 查询 实体
     * @param access_token
     * @return
     */
    public UserEntity findUserByToken(String access_token);

    /**
     * 注册
     * @param name
     * @param password
     * @return
     */
    public UserEntity register(String name,String password);

}
