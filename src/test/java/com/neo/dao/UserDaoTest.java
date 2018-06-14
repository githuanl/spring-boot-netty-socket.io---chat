package com.neo.dao;

import com.alibaba.fastjson.JSON;
import com.neo.controller.UserController;
import com.neo.entity.UserEntity;
import com.neo.serivce.UserSerivice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * Created by summer on 2017/5/5.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {

    @Autowired
    private UserSerivice serivice;

    @Test
    public void testSaveUser() throws Exception {
//        UserEntity user = new UserEntity();
//        user.setAuth_token(UUID.randomUUID().toString());
//        user.setUserName("90905555");
//        user.setPassword("456");
//        serivice.saveEntity(user);

        UserEntity user = serivice.register("tttt", "111111111");
        System.out.println("已生成ID：" + user.getId());

    }

    @Test
    public void findUserByUserName() {
//        UserEntity user = serivice.findUserByUserName("123");
//        System.out.println("user is " + user.toString());
    }

    @Test
    public void updateUser() {
        UserEntity user = new UserEntity();
        user.setUserName("天空232");
        user.setPassword("fffxxxx");
        user.setAuth_token(UUID.randomUUID().toString());
//        UserEntity userEntity = (UserEntity) serivice.updateEntityById("5b1f34e04fc8b10a1d6ac35e", user);
//        System.out.println(userEntity.toString());
    }

    @Test
    public void deleteUserById() {
//        serivice.deleteEntityById("");
    }

}
