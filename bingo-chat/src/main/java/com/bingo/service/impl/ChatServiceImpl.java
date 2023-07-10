package com.bingo.service.impl;

import com.bingo.config.ChatChannelConfig;
import com.bingo.service.ChatService;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author 徐志斌
 * @Date: 2023/5/28 20:30
 * @Version 1.0
 * @Description: SendServiceImpl
 */
@Service
public class ChatServiceImpl implements ChatService {
    /**
     * 发送消息给某个用户
     */
    @Override
    public void sendMsgByUserId(Long userId, String msg) throws Exception {
        ConcurrentHashMap<Long, Channel> userChannelMap = ChatChannelConfig.getUserChannelMap();
        Channel channel = userChannelMap.get(userId);
        if (ObjectUtils.isEmpty(channel)) {
            throw new Exception("用户信息不存在Netty服务端");
        }
        channel.writeAndFlush(new TextWebSocketFrame(msg));
    }

    /**
     * 消息群发
     */
    @Override
    public void sendMsgToAll(String msg) {
        ChannelGroup channelGroup = ChatChannelConfig.getChannelGroup();

//        ChatChannelConfig.getChannelGroup().writeAndFlush(new TextWebSocketFrame(msg));
    }
}
