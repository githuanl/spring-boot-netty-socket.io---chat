package com.neo.entity;

import com.neo.enums.AddMessageType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by liudong on 2018/6/26.
 */
@Document(collection = "t_add_message")
public class AddMessage extends BaseEntity {

    @Id
    private String id;

    private String fromUid;     //谁发起的请求

    private String toUid;       //发送给谁的申请,可能是群，那么就是创建该群组的用户
    private String groupId;     //创建人，群主

    private String remark;      //附言

    private String ext;     //扩展数据

    private AddMessageType msgResult = AddMessageType.Untreated;      //消息处理结果

    private String  type;       //类型 ，可能是添加好友或群组 friend 好友， group 群组

    private String  time;       //申请时间


    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFromUid() {
        return fromUid;
    }

    public void setFromUid(String fromUid) {
        this.fromUid = fromUid;
    }

    public String getToUid() {
        return toUid;
    }

    public void setToUid(String toUid) {
        this.toUid = toUid;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public AddMessageType getMsgResult() {
        return msgResult;
    }

    public void setMsgResult(AddMessageType msgResult) {
        this.msgResult = msgResult;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    @Override
    public String toString() {
        return "AddMessage{" +
                "id='" + id + '\'' +
                ", fromUid='" + fromUid + '\'' +
                ", toUid='" + toUid + '\'' +
                ", groupId='" + groupId + '\'' +
                ", remark='" + remark + '\'' +
                ", ext=" + ext +
                ", msgResult=" + msgResult +
                ", type='" + type + '\'' +
                ", time=" + time +
                '}';
    }
}
