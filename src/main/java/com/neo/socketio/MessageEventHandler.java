package com.neo.socketio;

import com.alibaba.fastjson.JSONObject;
import com.corundumstudio.socketio.*;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.neo.entity.AddMessage;
import com.neo.entity.GroupEntity;
import com.neo.entity.MessageEntity;
import com.neo.entity.UserEntity;
import com.neo.serivce.AddMessageSerivice;
import com.neo.serivce.ChatSerivice;
import com.neo.serivce.UserSerivice;
import com.neo.utils.DateUtils;
import com.neo.utils.SessionUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;


/**
 * Created by liudong on 2018/6/13.
 */
@Component
public class MessageEventHandler {

    private final SocketIOServer server;

    private Logger logger = LogManager.getLogger(getClass().getName());

    @Autowired
    UserSerivice userSerivice;
    @Autowired
    ChatSerivice chatSerivice;

    @Autowired
    AddMessageSerivice addMessageSerivice;


    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS");

    @Autowired
    public MessageEventHandler(SocketIOServer server) {
        this.server = server;
    }

    //添加connect事件，当客户端发起连接时调用，本文中将clientid与sessionid存入数据库
    //方便后面发送消息时查找到对应的目标client,
    @OnConnect
    public void onConnect(SocketIOClient client) {
        HandshakeData hd = client.getHandshakeData();

        String auth_token = hd.getSingleUrlParam("auth_token");
        UserEntity userEntity = userSerivice.findUserByToken(auth_token);
        String userId = userEntity.getId();
        String userName = userEntity.getUsername();
        client.set("userId", userId);
        client.set("userName", userName);
        SessionUtil.userId_socket_Map.put(userId, client);

        //上线关联所在的群组
        List<GroupEntity> entityList = userSerivice.findMyGroupsByUserId(userId);

        for (GroupEntity entity : entityList) {
            logger.info(userName + "自动关联了群 " + entity.getGroupname() + "   " + sdf.format(new Date()));
            client.joinRoom(entity.getId());
        }

        logger.info(userName + "---》上 === 线了  " + client.getSessionId() + "   " + sdf.format(new Date()));
    }


    //添加@OnDisconnect事件，客户端断开连接时调用，刷新客户端信息
    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        SessionUtil.userId_socket_Map.remove(client.get("userId"));
        logger.info(client.get("userName") + "---------》下 线了 " + sdf.format(new Date()));
    }


    //创建群
    @OnEvent(value = "creat")
    public void onEventCreat(SocketIOClient client, AckRequest ackRequest, MessageEntity msg) {

    }

    //申请加入群组
    @OnEvent(value = "addGroup")
    public void onEventJoin(SocketIOClient client, AckRequest ackRequest, AddMessage msg) {

        ackRequest.sendAckData("");
        String id = msg.getToUid();

        addMessageSerivice.saveAddMessage(msg);

        if (!StringUtils.isEmpty(id) && SessionUtil.userId_socket_Map.containsKey(id)) {
            SocketIOClient socketIOClient = SessionUtil.userId_socket_Map.get(id);
            socketIOClient.sendEvent("addGroup");
        }

        logger.info(msg.toString());
    }

    //拒绝加入群组
    @OnEvent(value = "refuseAddGroup")
    public void refuseAddGroup(SocketIOClient client, AckRequest ackRequest, JSONObject object) {

        ackRequest.sendAckData("");

        String id = object.getString("toUid");
        userSerivice.updateAddMessage(object.getString("messageBoxId"));
//
        if (!StringUtils.isEmpty(id) && SessionUtil.userId_socket_Map.containsKey(id)) {
            SocketIOClient socketIOClient = SessionUtil.userId_socket_Map.get(id);
            socketIOClient.sendEvent("refuseAddGroup");
        }
        logger.info(object.toString());
    }

    //同意加入群组
    @OnEvent(value = "agreeAddGroup")
    public void agreeAddGroup(SocketIOClient client, AckRequest ackRequest, JSONObject object) {

        ackRequest.sendAckData("");

        String id = object.getString("toUid");
        UserEntity entity = (UserEntity) userSerivice.getEntityById(id);
        userSerivice.updateAddMessage(entity, object.getString("groupId"), object.getString("messageBoxId"));
//
        if (!StringUtils.isEmpty(id) && SessionUtil.userId_socket_Map.containsKey(id)) {
            SocketIOClient socketIOClient = SessionUtil.userId_socket_Map.get(id);
            socketIOClient.sendEvent("agreeAddGroup");
        }
        logger.info(object.toString());
    }


    //离开群组(退群)
    @OnEvent(value = "leave")
    public void onEventLeave(SocketIOClient client, AckRequest ackRequest, MessageEntity msg) {

    }


    //群聊
    @OnEvent(value = "groupChat")
    public void onEventGroupChat(SocketIOClient client, AckRequest ackRequest, MessageEntity msg) {
//        server.getRoomOperations()
    }


    //消息接收入口，当接收到消息后，查找发送目标客户端，并且向该客户端发送消息，且给自己发送消息
    @OnEvent(value = "chat")
    public void onEvent(SocketIOClient client, AckRequest ackRequest, MessageEntity msg) {

        if (msg.getFrom_user().equals(msg.getTo_user())) {
            ackRequest.sendAckData("请不要给自己发消息");
            return;
        }

        //将数据保存到服务器
        chatSerivice.saveMessageData(msg);

        boolean isChat = msg.getChat_type().toString().equals("chat");

        //ack 返回数据 服务器收到发送的数据
        if (ackRequest.isAckRequested()) {
            String toName = "";
            if (isChat) {
                toName = msg.getTo_user();
            } else {
                toName = "群： " + msg.getTo_user();
            }
            logger.info("给 " + toName + " 发送的数据 服务器已经收到， 日期： " + sdf.format(new Date()));
            //发送ack回调数据到客户端
            ackRequest.sendAckData(msg);
        }


        String to_user_id = msg.getTo_user_id(); //如果是 群聊，则对应群的id
        String to_user_name = msg.getFrom_user();

        if (isChat) { //单聊
            // 如果对方在线 则找到对应的client 给其发送消息
            if (SessionUtil.userId_socket_Map.containsKey(to_user_id)) {
                SessionUtil.userId_socket_Map.get(to_user_id).sendEvent("chat", new AckCallback<String>(String.class) {
                    //对方客户端接收到消息 返回给服务器端
                    @Override
                    public void onSuccess(String result) {
                        logger.info(to_user_name + "已收到消息 ， ack 回复 ： " + result + "    日期： " + sdf.format(new Date()));
                    }

                    //发送消息超时
                    @Override
                    public void onTimeout() {
                        System.out.println(to_user_name + "---------》发送消息超时 " + sdf.format(new Date()));
                    }
                }, msg);
            } else { //如果 下线 转apns 消息推送
                logger.info(to_user_name + "---------》需要转 apns 消息推送 " + sdf.format(new Date()));
            }
        } else {  //群聊

            logger.info("========================发送群消息==================================");

//            server.getBroadcastOperations().sendEvent("groupChat",msg); //系统广播
            // 房间（群内）广播
            server.getRoomOperations(to_user_id).sendEvent("groupChat", msg, new BroadcastAckCallback<String>(String.class) {
                @Override
                protected void onClientTimeout(SocketIOClient client) {
                    logger.info("群消息: " + client.get("userName") + " 发送超时了");
                }

                @Override
                protected void onClientSuccess(SocketIOClient client, String result) {
                    logger.info("群消息: " + client.get("userName") + " 已接收到" + DateUtils.getDataTimeYMDHMSS());
                }
            });
        }

    }


    public void sendMessageToAllClient(String userName) {
        Collection<SocketIOClient> clients = server.getAllClients();
        for (SocketIOClient client : clients) {

        }
    }
//
}
