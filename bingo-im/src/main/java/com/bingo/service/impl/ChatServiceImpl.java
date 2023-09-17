package com.bingo.service.impl;

import com.alibaba.fastjson.JSON;
import com.bingo.adapter.ChatRecordAdapter;
import com.bingo.config.ThreadPoolConfig;
import com.bingo.constant.MQConstant;
import com.bingo.kafka.KafkaProducer;
import com.bingo.pojo.dto.im.ChatRecordDTO;
import com.bingo.pojo.po.im.BingoChatSendRecord;
import com.bingo.service.ChatService;
import com.bingo.store.BingoChatSendRecordStore;
import com.bingo.strategy.chat.AbstractChatStrategy;
import com.bingo.strategy.chat.StrategyChatFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

/**
 * @Author 徐志斌
 * @Date: 2023/5/28 20:30
 * @Version 1.0
 * @Description: SendServiceImpl
 */
@Slf4j
@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    private BingoChatSendRecordStore recordStore;
    @Autowired
    private KafkaProducer producer;
    @Autowired
    @Qualifier(ThreadPoolConfig.CHAT_POOL_NAME)
    private ThreadPoolTaskExecutor taskExecutor;

    /**
     * 发送消息给用户
     */
    @Override
    public void sendChatRecord(ChatRecordDTO msgDTO) {
        // 保存聊天记录（保存成功，才算发送成功）
        BingoChatSendRecord sendRecord = ChatRecordAdapter.buildChatRecordPO(msgDTO);
        recordStore.saveChatRecord(sendRecord);

        // 根据消息类型获取对应策略类
        taskExecutor.submit(() -> {
            AbstractChatStrategy strategyHandler = StrategyChatFactory.getStrategyHandler(msgDTO.getType());
            strategyHandler.handleChatRecord(sendRecord);
        });

        // 通过Channel发送消息
        producer.sendMessage(MQConstant.IM_SEND_TOPIC, JSON.toJSONString(sendRecord));
    }
}
