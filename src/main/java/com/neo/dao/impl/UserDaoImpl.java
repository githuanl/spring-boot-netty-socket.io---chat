package com.neo.dao.impl;

import com.mongodb.WriteResult;
import com.neo.dao.BaseDao;
import com.neo.dao.UserDao;
import com.neo.entity.BaseEntity;
import com.neo.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by summer on 2017/5/5.
 */
@Component
public class UserDaoImpl<T extends BaseEntity> extends BaseDaoImpl<UserEntity> implements UserDao<UserEntity> {

    /**
     * 根据用户名查询对象
     *
     * @param name
     * @return
     */
    @Override
    public UserEntity findUserByUserName(String name) {
        Query query = new Query(Criteria.where("userName").is(name));
        UserEntity user = mongoTemplate.findOne(query, UserEntity.class);
        return user;
    }

    /**
     * 根据用户Token查询对象
     *
     * @param access_token
     * @return
     */
    @Override
    public UserEntity findUserByToken(String access_token) {
        Query query = new Query(Criteria.where("auth_token").is(access_token));
        UserEntity user = mongoTemplate.findOne(query, UserEntity.class);
        return user;
    }

}
