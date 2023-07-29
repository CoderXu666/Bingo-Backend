package com.bingo.im.websocket;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

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
@ServerEndpoint("/msgServer/{userId}")
@Component
@Scope("prototype")
public class WebSocketServer {
    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
     */
    private static ConcurrentHashMap<String, Session> webSocketMap = new ConcurrentHashMap<>();
    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;
    /**
     * 接收userId
     */
    private String userId = "";

    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        this.session = session;
        this.userId = userId;
        webSocketMap.put(userId, session);
        System.out.println("首次连接调用");
    }

    @OnMessage
    public void onMessage(String message) {
        try {
            System.out.println("发送信息");
            this.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnError
    public void onError(Throwable error) {
        System.out.println("连接异常...");
        error.printStackTrace();
    }

    @OnClose
    public void onClose() {
        System.out.println("连接关闭");
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
                continue;
            }
            if (webSocketMap.get(key) == null) {
                webSocketMap.remove(key);
                continue;
            }
            Session sessionValue = webSocketMap.get(key);
            if (sessionValue.isOpen()) {
                sessionValue.getBasicRemote().sendText(message);
            } else {
                sessionValue.close();
                webSocketMap.remove(key);
            }
        }
    }
}
