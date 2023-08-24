package com.bingo.service.impl;

import com.bingo.im.netty.NettyChannelRelation;
import com.bingo.pojo.dto.im.ChatMsgDTO;
import com.bingo.service.ChatService;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
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
    @Autowired
    private KafkaTemplate kafkaTemplate;

    /**
     * 发送消息给某个用户
     */
    @Override
    public void sendMsgByUserId(Long userId, String msg) throws Exception {
        ConcurrentHashMap<Long, Channel> userChannelMap = NettyChannelRelation.getUserChannelMap();
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
        ChannelGroup channelGroup = NettyChannelRelation.getChannelGroup();
        channelGroup.writeAndFlush(new TextWebSocketFrame(msg));
    }

    /**
     * 发送消息给某个群聊
     */
    @Override
    public void sendMsg2Group(ChatMsgDTO msgDTO) {
        // 根据群id，查询所有的群成员
        // 根据userId取出所有的channel
        // 使用channel进行消息发送
    }
}
