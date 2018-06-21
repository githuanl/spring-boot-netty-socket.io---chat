package com.neo.entity;

import com.neo.enums.Chat_type;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by liudong on 2018/6/8.
 */
@Document(collection = "t_message")
public class MessageEntity extends BaseEntity {

//        msg_id: String,            //消息id uuid
//        timestamp: Number,        //发送(服务器)时间
//        sendtime: Number,        //客户端发送时间
//        from_user: String,       //发送人

//        to_user: String,         //要发送的人
//        chat_type: String,      //聊天的类型 chat(单聊)    groupChat (群聊)
//        group_id: String,        //群聊 房间id (群聊时房间id)
//        group_name: String,      //群聊 房间id (群聊时房间name)
//        ext: String,            //扩展
//        bodies: String          //内容 json 文本 存储
    //类型1-->文本类型  { "type":"txt","msg":"hello from test2"}
    //类型2-->图片类型  { "type":"img","imgUrl":"hello from test2","imageName","图片名称"  //消息内容}

    @Id
    private String msg_id;          //消息id uuid
    private long timestamp;         //发送(服务器)时间
    private String sendtime;        //客户端发送时间
    private String from_user;       //
    private String from_user_id;     //
    private String to_user;         //
    private String to_user_id;
    private Chat_type chat_type;       //
    private String group_id;       //
    private String group_name;      //
    private String ext;             // 扩展数据
    private Bodies bodies;          //

    public String getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(String msg_id) {
        this.msg_id = msg_id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getSendtime() {
        return sendtime;
    }

    public void setSendtime(String sendtime) {
        this.sendtime = sendtime;
    }

    public String getFrom_user() {
        return from_user;
    }

    public void setFrom_user(String from_user) {
        this.from_user = from_user;
    }

    public String getTo_user() {
        return to_user;
    }

    public void setTo_user(String to_user) {
        this.to_user = to_user;
    }


    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public Chat_type getChat_type() {
        return chat_type;
    }

    public void setChat_type(Chat_type chat_type) {
        this.chat_type = chat_type;
    }

    public Bodies getBodies() {
        return bodies;
    }

    public void setBodies(Bodies bodies) {
        this.bodies = bodies;
    }

    public String getFrom_user_id() {
        return from_user_id;
    }

    public void setFrom_user_id(String from_user_id) {
        this.from_user_id = from_user_id;
    }

    public String getTo_user_id() {
        return to_user_id;
    }

    public void setTo_user_id(String to_user_id) {
        this.to_user_id = to_user_id;
    }

    @Override
    public String toString() {
        return "MessageEntity{" +
                "msg_id='" + msg_id + '\'' +
                ", timestamp=" + timestamp +
                ", sendtime='" + sendtime + '\'' +
                ", from_user='" + from_user + '\'' +
                ", to_user='" + to_user + '\'' +
                ", chat_type=" + chat_type +
                ", group_id='" + group_id + '\'' +
                ", group_name='" + group_name + '\'' +
                ", ext='" + ext + '\'' +
                ", bodies=" + bodies +
                '}';
    }
}
