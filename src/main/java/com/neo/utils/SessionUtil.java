package com.neo.utils;

import com.corundumstudio.socketio.SocketIOClient;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionUtil {

    //保存所有的 user ：socket 连接
    public static Map<String, SocketIOClient> userId_socket_Map = new ConcurrentHashMap<>();

}
