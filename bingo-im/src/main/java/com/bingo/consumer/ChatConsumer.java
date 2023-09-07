package com.bingo.consumer;

import com.alibaba.fastjson.JSON;
import com.bingo.constant.MQConstant;
import com.bingo.netty.NettyChannelRelation;
import com.bingo.pojo.dto.im.ChatMsgDTO;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-09-07  13:21
 * @Description: IM聊天通讯Consumer
 * @Version: 1.0
 */
@Slf4j
@Component
public class ChatConsumer {
    /**
     * 发送聊天消息
     */
    @KafkaListener(topics = MQConstant.IM_SEND_TOPIC, groupId = MQConstant.IM_GROUP_ID)
    public void sendMsg(String message) {
        ChatMsgDTO msgDTO = JSON.parseObject(message, ChatMsgDTO.class);

        // 接收方channel
        Channel channel = NettyChannelRelation.getUserChannelMap().get(msgDTO.getGoalId());

        // 通过Channel发送消息到客户端（在线状态直接推，不在线就不推送了）
        if (ObjectUtils.isNotEmpty(channel)) {
            channel.writeAndFlush(new TextWebSocketFrame(msgDTO.toString()));
        }
    }
}
