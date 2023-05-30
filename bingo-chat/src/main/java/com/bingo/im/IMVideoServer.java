package com.bingo.im;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author 徐志斌
 * @Date: 2023/5/30 20:15
 * @Version 1.0
 * @Description: 直播视频 WebSocket 服务器(WebRTC实现基于WebSocket)
 */
@Component
@ServerEndpoint("/msgServer/{userId}")
public class IMVideoServer {
    /**
     * 1.记录当前在线连接数(应该设计为设计成线程安全)
     * 2.存放每个客户端对应的WebSocket对象(K:userId, V:Session),
     */
    private static int onlineCount = 0;
    private static ConcurrentHashMap<String, Session> webSocketMap = new ConcurrentHashMap<>();
    private Session session;
    private String userId = "";

    /**
     * 客户端与WebSocket服务器建立连接
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        this.session = session;
        this.userId = userId;
        webSocketMap.put(userId, session);
        System.out.println("--------------IM Video 建立成功--------------");
    }

    /**
     * 接受客户端发送来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        try {
            this.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发生异常
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("---------报错了------:");
    }

    /**
     * 释放连接
     */
    @OnClose
    public void onClose() {
        System.out.println("---------断开连接------:");
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        if (message.equals("心跳")) {
            this.session.getBasicRemote().sendText(message);
        }
        Enumeration<String> keys = webSocketMap.keys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            if (key.equals(this.userId)) {
                System.err.println("my id " + key);
                continue;
            }
            if (webSocketMap.get(key) == null) {
                webSocketMap.remove(key);
                System.err.println(key + " : null");
                continue;
            }
            Session sessionValue = webSocketMap.get(key);
            if (sessionValue.isOpen()) {
                System.out.println("发消息给: " + key + " ,message: " + message);
                sessionValue.getBasicRemote().sendText(message);
            } else {
                System.err.println(key + ": not open");
                sessionValue.close();
                webSocketMap.remove(key);
            }
        }
    }

    /**
     * 发送自定义消息
     */
    public static void sendInfo(String message, @PathParam("userId") String userId) throws IOException {
        System.out.println("发送消息到:" + userId + "，内容:" + message);
        if (!StringUtils.isEmpty(userId) && webSocketMap.containsKey(userId)) {
            webSocketMap.get(userId).getBasicRemote().sendText(message);
            //webSocketServer.sendMessage(message);
        } else {
            System.out.println("用户" + userId + ",不在线！");
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        IMVideoServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        IMVideoServer.onlineCount--;
    }

}
