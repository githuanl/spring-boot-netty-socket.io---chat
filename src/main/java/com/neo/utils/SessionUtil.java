package com.neo.utils;

import com.corundumstudio.socketio.SocketIOClient;
import com.neo.entity.UserEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionUtil {

    //保存所有的 user ：socket 连接
    public static Map<String, SocketIOClient> user_socket_Map = new ConcurrentHashMap<>();

}
