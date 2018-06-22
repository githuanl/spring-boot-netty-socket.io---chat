package com.neo.serivce.impl;

import com.neo.dao.UserDao;
import com.neo.entity.BaseEntity;
import com.neo.entity.GroupEntity;
import com.neo.entity.GroupUser;
import com.neo.entity.UserEntity;
import com.neo.serivce.UserSerivice;
import com.neo.utils.DateUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
    public UserEntity register(String name, String password, String avatar) {

        //获取当前日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        //获取三十天后日期
        Calendar theCa = Calendar.getInstance();
        theCa.setTime(today);
        theCa.add(theCa.DATE, 30);
        Date start = theCa.getTime();
        String startDate = sdf.format(start);//三十天之后日期

        UserEntity user = new UserEntity();
        user.setAuth_token(UUID.randomUUID().toString());
        user.setAuth_date(startDate);
        user.setUsername(name);
        user.setPassword(password);
        user.setAvatar(avatar);
        user.setSign("一路有你");
        userDao.saveEntity(user);
        return user;
    }

    @Transactional
    @Override
    public GroupEntity creatGroup(String name, String avatar, UserEntity user) {

        //创建 群组
        GroupEntity entity = new GroupEntity();
        entity.setCreat_date(DateUtils.getDataTimeYMD());
        entity.setGroupname(name);
        entity.setUser_id(user.getId());
        entity.setUser_name(user.getUsername());
        entity.setAvatar(avatar);
        userDao.saveEntity(entity);

        //把自己加入群组
        GroupUser groupUser = new GroupUser();
        groupUser.setId(entity.getId());
        groupUser.setUser_id(user.getId());
        groupUser.setUsername(user.getUsername());
        groupUser.setAvatar(user.getAvatar());
        groupUser.setSign(user.getSign());

        userDao.saveEntity(groupUser);

        return entity;
    }

    /**
     * 获取 我所在的 所有的群
     *
     * @return
     */
    @Override
    public List<GroupEntity> findMyGroupsByUserId(String id) {
        return userDao.findMyGroupsByUserId(id);
    }

    /**
     * 根据群id  获取 群下面的所有成员
     *
     * @return
     */
    @Override
    public List<GroupUser> findUsersByGroupId(String group_id) {
        return userDao.findUsersByGroupId(group_id);
    }

    /**
     * 根据群的名字查询所有的群
     *
     * @return
     */
    @Override
    public List<GroupEntity> findGroupsByGroupName(String groupName) {
        return userDao.findGroupsByGroupName(groupName);
    }


}
