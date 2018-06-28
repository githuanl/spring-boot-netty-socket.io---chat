package com.neo.dao.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.neo.dao.UserDao;
import com.neo.entity.BaseEntity;
import com.neo.entity.UserEntity;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

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
        Query query = new Query(Criteria.where("username").is(name));
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

    /**
     * 根据用户名查询 对应的人员
     *
     * @return
     */
    @Override
    public List<UserEntity> findUsersByName(String page, String name) {
        List<UserEntity> list;
        if (StringUtils.isEmpty(name)) {
            list = mongoTemplate.findAll(UserEntity.class);
        } else {
            Query query = new Query(Criteria.where("String").regex(name));
            list = mongoTemplate.find(query, UserEntity.class);
        }
        return list;
    }


    @Override
    public List<UserEntity> selectAll() {
        DBObject dbObject = new BasicDBObject();
        //dbObject.put("name", "zhangsan");  //查询条件

        BasicDBObject fieldsObject = new BasicDBObject();
        //指定返回的字段
        fieldsObject.put("id", true);
        fieldsObject.put("username", true);
        fieldsObject.put("avatar", true);
        fieldsObject.put("sign", true);
        fieldsObject.put("nickname", true);

        Query query = new BasicQuery(dbObject, fieldsObject);
        List<UserEntity> userEntities = mongoTemplate.find(query, UserEntity.class);
        return userEntities;
    }


}
