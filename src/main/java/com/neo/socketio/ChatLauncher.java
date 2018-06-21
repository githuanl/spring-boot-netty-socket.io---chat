package com.neo.socketio;

import com.corundumstudio.socketio.listener.*;
import com.corundumstudio.socketio.*;
import com.neo.entity.MessageEntity;
import com.neo.entity.UserEntity;
import com.neo.serivce.ChatSerivice;
import com.neo.serivce.UserSerivice;
import com.neo.utils.SessionUtil;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

public class ChatLauncher {
//保留代码 最初的代码====
//public class ChatLauncher implements ApplicationRunner {

//    @Autowired
//    UserSerivice userSerivice;
//
//    @Value("${im.server.host}")
//    private String host;
//    @Value("${im.server.port}")
//    private Integer port;
//
//    @Autowired
//    ChatSerivice chatSerivice;
//
//    private static SocketIOServer server;
//
//    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss ");
//
//    @Override
//    public void run(ApplicationArguments applicationArguments) throws Exception {
//
//        Configuration config = new Configuration();
//        config.setHostname(host);
//        config.setPort(port);
//        config.setPingInterval(5000);
//        config.setPingTimeout(3000);
//        //设置最大每帧处理数据的长度，防止他人利用大数据来攻击服务器
//        config.setMaxFramePayloadLength(1024 * 1024);
//        //设置http交互最大内容长度
//        config.setMaxHttpContentLength(1024 * 1024);
//
////        // Instantiate Redisson configuration
////        Config redissonConfig = new Config();
////        redissonConfig.useSingleServer().setAddress("127.0.0.1:6379");
////
////// Instantiate Redisson connection
////        RedissonClient redisson = Redisson.create(redissonConfig);
////
////// Instantiate RedissonClientStoreFactory
////        RedissonStoreFactory redisStoreFactory = new RedissonStoreFactory(redisson);
////        config.setStoreFactory(redisStoreFactory);
//
//        config.setAuthorizationListener(hd -> {
//
////          System.out.println(hd.getUrlParams());
//
//            String auth_token = hd.getSingleUrlParam("auth_token");
//
//            if (StringUtil.isNullOrEmpty(auth_token)) {
//                return false;
//            }
//
//            // 移动设备不能同时登录 (android ios) 待处理
//
//            //是否拦截 socket.io 连接
//            return userSerivice.findUserByToken(auth_token) == null ? false : true;
//        });
//
//        server = new SocketIOServer(config);
//
//        server.addConnectListener(client -> {
//
//            HandshakeData hd = client.getHandshakeData();
//            String auth_token = hd.getSingleUrlParam("auth_token");
//
//            UserEntity userEntity = userSerivice.findUserByToken(auth_token);
//            String userName = userEntity.getUsername();
//            //连接上线。
//            SessionUtil.allLineUser.add(userName);
//            client.set("userName", userName);
//
//            SessionUtil.user_socket_Map.put(userName, client);
//
//            System.out.println(userName + "---》上 线了 " + sdf.format(new Date()));
//        });
//
//
//        server.addDisconnectListener(client -> {
//            HandshakeData hd = client.getHandshakeData();
//            String userName = client.get("userName");
//            SessionUtil.allLineUser.remove(userName);
//            SessionUtil.user_socket_Map.remove(userName);
//
//            System.out.println(userName + "---------》下 线了 " + sdf.format(new Date()));
//        });
//
//        server.addEventListener("chat", MessageEntity.class, (client, msg, ackRequest) -> {
//
//
//            //将数据保存到服务器
//            chatSerivice.saveMessageData(msg);
//
//            //ack 返回数据 服务器收到发送的数据
//            if (ackRequest.isAckRequested()) {
//                System.out.println("给 " + msg.getTo_user() + " 发送的数据 服务器已经收到， 日期： " + sdf.format(new Date()));
//                //发送ack回调数据到客户端
//                ackRequest.sendAckData(msg);
//            }
//
//            String to_user = msg.getTo_user();
//
//            // 如果对方在线 则找到对应的client 给其发送消息
//            if (SessionUtil.allLineUser.contains(to_user)) {
//
//                SessionUtil.user_socket_Map.get(to_user).sendEvent("chat", new AckCallback<String>(String.class, 5) {
//
//                    //对方客户端接收到消息 返回给服务器端
//                    @Override
//                    public void onSuccess(String result) {
//                        System.out.println("给" + to_user + "发送的数据已收到 ， 回复内容 ： " + result + "    日期： " + sdf.format(new Date()));
//                    }
//
//                    //发送消息超时
//                    @Override
//                    public void onTimeout() {
//                        System.out.println(to_user + "---------》需要转 apns 消息推送 " + sdf.format(new Date()));
//                    }
//                }, msg);
//            } else { //如果 下线 转apns 消息推送
//                System.out.println(to_user + "---------》需要转 apns 消息推送 " + sdf.format(new Date()));
//            }
//
//
////                server.getBroadcastOperations().sendEvent("chat", msg);
//
//        });
//
//        server.start();
//
//    }
//
//
//    /**
//     * 给所有连接客户端推送消息
//     *
//     * @param eventType 推送的事件类型
//     * @param message   推送的内容
//     */
//    public void sendMessageToAllClient(String eventType, String message) {
//        Collection<SocketIOClient> clients = server.getAllClients();
//        for (SocketIOClient client : clients) {
//            client.sendEvent(eventType, message);
//        }
//    }
//
//
//    /**
//     * 获得SocketIOServer服务对象
//     *
//     * @return
//     */
//    public static SocketIOServer getSocketIOServer() {
//        return server;
//    }


}
