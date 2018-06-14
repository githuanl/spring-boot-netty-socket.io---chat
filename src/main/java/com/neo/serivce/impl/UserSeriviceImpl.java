package com.neo.serivce.impl;

import com.neo.dao.UserDao;
import com.neo.entity.BaseEntity;
import com.neo.entity.UserEntity;
import com.neo.serivce.UserSerivice;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Created by liudong on 2018/6/8.
 */
@Component
public class UserSeriviceImpl<T extends BaseEntity> extends BaseSeriviceImpl<UserEntity> implements UserSerivice<UserEntity> {

    @Resource
    UserDao userDao;

    @Override
    public UserEntity findUserByUserName(String name) {
        return userDao.findUserByUserName(name);
    }

    @Override
    public UserEntity findUserByToken(String access_token) {
        return userDao.findUserByToken(access_token);
    }

    /**
     * 注册
     *
     * @param name
     * @param password
     * @return
     */
    @Override
    public UserEntity register(String name, String password) {

        //获取当前日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        //获取三十天后日期
        Calendar theCa = Calendar.getInstance();
        theCa.setTime(today);
        theCa.add(theCa.DATE, 30);//最后一个数字30可改，30天的意思
        Date start = theCa.getTime();
        String startDate = sdf.format(start);//三十天之后日期

        UserEntity user = new UserEntity();
        user.setAuth_token(UUID.randomUUID().toString());
        user.setAuth_date(startDate);
        user.setUserName(name);
        user.setPassword(password);
        userDao.saveEntity(user);
        return user;
    }


}
