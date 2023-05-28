package com.bingo.service.impl;

import com.bingo.config.NettyChannelConfig;
import com.bingo.service.SendService;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author 徐志斌
 * @Date: 2023/5/28 20:30
 * @Version 1.0
 * @Description: SendServiceImpl
 */
@Service
public class SendServiceImpl implements SendService {
    /**
     * 发送消息给某个用户
     */
    @Override
    public void sendMsgToOne(String userId, String msg) {
        ConcurrentHashMap<String, Channel> userChannelMap = NettyChannelConfig.getUserChannelMap();
        Channel channel = userChannelMap.get(userId);
        channel.writeAndFlush(new TextWebSocketFrame(msg));
    }

    /**
     * 消息群发
     */
    @Override
    public void sendMsgToAll(String msg) {
        NettyChannelConfig.getChannelGroup().writeAndFlush(new TextWebSocketFrame(msg));
    }
}
