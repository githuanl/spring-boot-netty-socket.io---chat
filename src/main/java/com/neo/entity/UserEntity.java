package com.neo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by lh on 2018/6/5.
 */
@Document(collection = "t_user")
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
    private String username;
    private String password;
    private String auth_token;   //token
    private String auth_date;   //过期时间
    private String status = "online";      //在线状态 online：在线、hide：隐身
    private String sign;        //我的签名
    private String avatar;      //头像
    private String nickname;    //昵称

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", auth_token='" + auth_token + '\'' +
                ", auth_date='" + auth_date + '\'' +
                ", status='" + status + '\'' +
                ", sign='" + sign + '\'' +
                ", avatar='" + avatar + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
