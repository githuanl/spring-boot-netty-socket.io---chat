package com.neo.socketio;

import com.corundumstudio.socketio.*;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.neo.entity.MessageEntity;
import com.neo.entity.UserEntity;
import com.neo.serivce.ChatSerivice;
import com.neo.serivce.UserSerivice;
import com.neo.utils.SessionUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;


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

        String userName = userEntity.getUserName();
        client.set("userName", userName);
        SessionUtil.user_socket_Map.put(userName, client);

        logger.info(userName + "---》上 === 线了  " + client.getSessionId() + "   " + sdf.format(new Date()));
    }


    //添加@OnDisconnect事件，客户端断开连接时调用，刷新客户端信息
    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        SessionUtil.user_socket_Map.remove(client.get("userName"));
        logger.info(client.get("userName") + "---------》下 线了 " + sdf.format(new Date()));
    }

    //消息接收入口，当接收到消息后，查找发送目标客户端，并且向该客户端发送消息，且给自己发送消息
    @OnEvent(value = "chat")
    public void onEvent(SocketIOClient client, AckRequest ackRequest, MessageEntity msg) {
        //将数据保存到服务器
        chatSerivice.saveMessageData(msg);

        //ack 返回数据 服务器收到发送的数据
        if (ackRequest.isAckRequested()) {
            logger.info("给 " + msg.getTo_user() + " 发送的数据 服务器已经收到， 日期： " + sdf.format(new Date()));
            //发送ack回调数据到客户端
            ackRequest.sendAckData(msg);
        }

        String to_user = msg.getTo_user();
        // 如果对方在线 则找到对应的client 给其发送消息
        if (SessionUtil.user_socket_Map.containsKey(to_user)) {

            SessionUtil.user_socket_Map.get(to_user).sendEvent("chat", new AckCallback<String>(String.class, 5) {

                //对方客户端接收到消息 返回给服务器端
                @Override
                public void onSuccess(String result) {
                    logger.info("给" + to_user + "发送的数据已收到 ， 回复内容 ： " + result + "    日期： " + sdf.format(new Date()));
                }

                //发送消息超时
                @Override
                public void onTimeout() {
                    System.out.println(to_user + "---------》发送消息超时 " + sdf.format(new Date()));
                }
            }, msg);
        } else { //如果 下线 转apns 消息推送
            logger.info(to_user + "---------》需要转 apns 消息推送 " + sdf.format(new Date()));
        }
    }


    public void sendMessageToAllClient(String userName) {
        Collection<SocketIOClient> clients = server.getAllClients();
        for (SocketIOClient client : clients) {

        }
    }
//
}
