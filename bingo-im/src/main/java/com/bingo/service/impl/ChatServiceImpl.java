package com.bingo.service.impl;

import com.alibaba.fastjson.JSON;
import com.bingo.adapter.ChatRecordAdapter;
import com.bingo.config.ThreadPoolConfig;
import com.bingo.constant.MQConstant;
import com.bingo.kafka.KafkaProducer;
import com.bingo.pojo.dto.im.ChatRecordDTO;
import com.bingo.pojo.po.im.BingoChatSendRecord;
import com.bingo.pojo.po.im.BingoChatShow;
import com.bingo.service.ChatService;
import com.bingo.store.BingoChatSendRecordStore;
import com.bingo.store.BingoChatShowStore;
import com.bingo.strategy.chat.AbstractChatStrategy;
import com.bingo.strategy.chat.StrategyChatFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

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
    private BingoChatShowStore showStore;
    @Autowired
    private KafkaProducer producer;
    @Autowired
    @Qualifier(ThreadPoolConfig.CHAT_POOL_NAME)
    private ThreadPoolTaskExecutor taskExecutor;

    /**
     * 发送消息给用户
     */
    @Override
    public void sendChatRecord(ChatRecordDTO msgDTO, MultipartFile file) {
        // 保存聊天记录（保存成功，才算发送成功）
        BingoChatSendRecord sendRecord = ChatRecordAdapter.buildChatRecordPO(msgDTO);
        recordStore.saveChatRecord(sendRecord);

        // 根据消息类型获取对应策略类
        taskExecutor.submit(() -> {
            AbstractChatStrategy strategyHandler = StrategyChatFactory.getStrategyHandler(msgDTO.getType());
            try {
                strategyHandler.handleChatRecord(sendRecord, file);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        // 处理聊天会话窗口
        taskExecutor.submit(() -> {
            this.handleChatShow(msgDTO.getUid(), msgDTO.getGoalId());
        });

        // 通过Channel发送消息
        producer.sendMessage(MQConstant.IM_SEND_TOPIC, JSON.toJSONString(sendRecord));
    }

    /**
     * 处理聊天会话窗口（未读数等.....）
     */
    public Boolean handleChatShow(Long uid, Long goalId) {
        BingoChatShow uRecord = showStore.getOneRecord(uid, goalId);
        BingoChatShow goalRecord = showStore.getOneRecord(goalId, uid);
        if (ObjectUtils.isEmpty(uRecord)) {
            showStore.saveRecord(uid, goalId);
        } else if (ObjectUtils.isEmpty(goalRecord)) {
            showStore.saveRecord(goalId, uid);
        }
        // 更新未读数量
        uRecord.setUnreadCount(uRecord.getUnreadCount() + 1);
        uRecord.setReceiveTime(new Date());
        return showStore.updateRecordById(uRecord);
    }
}
