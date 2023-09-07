package com.bingo.service.impl;

import com.bingo.constant.MQConstant;
import com.bingo.netty.NettyChannelRelation;
import com.bingo.pojo.dto.im.ChatMsgDTO;
import com.bingo.service.ChatService;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

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
     * 发送消息给用户
     */
    @Override
    public void sendChatByUid(ChatMsgDTO msgDTO) {
        // 异步保存聊天记录、未读数量
        taskExecutor.execute(() -> {

        });

        // MQ保存聊天消息
        kafkaTemplate.send(MQConstant.IM_SEND_TOPIC, msgDTO);
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
