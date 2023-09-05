package com.bingo.service.impl;

import com.bingo.constant.MQConstant;
import com.bingo.netty.NettyChannelRelation;
import com.bingo.pojo.dto.im.ChatMsgDTO;
import com.bingo.service.ChatService;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
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
    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    /**
     * 发送消息给某个用户
     */
    @Override
    public void sendMsgByUserId(ChatMsgDTO msgDTO) throws Exception {
        ConcurrentHashMap<Long, Channel> userChannelMap = NettyChannelRelation.getUserChannelMap();
        Channel channel = userChannelMap.get(msgDTO.getGoalId());
        if (ObjectUtils.isEmpty(channel)) {
            throw new Exception("用户Channel频道不存在.....");
        }

        // 线程池异步：通过Channel发送消息到客户端
        taskExecutor.execute(() -> {
            channel.writeAndFlush(new TextWebSocketFrame(msgDTO.getMsg()));
        });

        // 异步保存聊天消息
        kafkaTemplate.send(MQConstant.IM_SEND_MSG_TOPIC, msgDTO);
    }

    /**
     * 消息群发
     */
    @Override
    public void sendMsgToAll(String msg) {
        ChannelGroup channelGroup = NettyChannelRelation.getChannelGroup();
        channelGroup.writeAndFlush(new TextWebSocketFrame(msg));
    }
}
