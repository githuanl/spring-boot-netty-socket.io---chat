package com.neo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by lh on 2018/6/5.
 */
@Document(collection = "user")
public class UserEntity extends BaseEntity {

//    userId: Number,
//    name: String,
//    headImageUrl: String,   //头像
//    nickname: String,       //昵称
//    password: String,
//    auth_token: String,
//    auth_date: Number

    @Id
    private String id;
    private String userName;
    private String password;
    private String auth_token;
    private String auth_date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuth_token() {
        return auth_token;
    }

    public void setAuth_token(String auth_token) {
        this.auth_token = auth_token;
    }

    public String getAuth_date() {
        return auth_date;
    }

    public void setAuth_date(String auth_date) {
        this.auth_date = auth_date;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", auth_token='" + auth_token + '\'' +
                ", auth_date='" + auth_date + '\'' +
                '}';
    }
}
