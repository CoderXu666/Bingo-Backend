package com.bingo.service.impl;

import com.bingo.adapter.ChatRecordAdapter;
import com.bingo.constant.MQConstant;
import com.bingo.netty.NettyChannelRelation;
import com.bingo.pojo.dto.im.ChatMsgDTO;
import com.bingo.pojo.po.im.BingoChatSendRecord;
import com.bingo.pojo.po.im.BingoChatShow;
import com.bingo.service.ChatService;
import com.bingo.store.BingoChatSendRecordStore;
import com.bingo.store.BingoChatShowStore;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.Date;

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
    @Autowired
    private BingoChatSendRecordStore recordStore;
    @Autowired
    private BingoChatShowStore showStore;

    /**
     * 发送消息给用户
     */
    @Override
    public void sendChatByUid(ChatMsgDTO msgDTO) {
        // 保存聊天记录
        BingoChatSendRecord sendRecord = ChatRecordAdapter.chatDTO2ChatRecord(msgDTO);
        recordStore.saveChatRecord(sendRecord);

        // 更新未读数量（TODO 并发问题考虑一下）
        taskExecutor.execute(() -> {
            BingoChatShow record = showStore.getOneShowRecord(msgDTO.getGoalId(), msgDTO.getUid());
            record.setUnreadCount(record.getUnreadCount() + 1);
            record.setReceiveTime(new Date());
            showStore.updateShowRecord(record);
        });

        // 通过Channel发送消息（等待聊天记录保存成功了在推送，要不会出现问题）
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
