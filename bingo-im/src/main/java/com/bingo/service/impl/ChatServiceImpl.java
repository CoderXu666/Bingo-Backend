package com.bingo.service.impl;

import com.bingo.adapter.ChatRecordAdapter;
import com.bingo.constant.MQConstant;
import com.bingo.pojo.dto.im.ChatRecordDTO;
import com.bingo.pojo.po.im.BingoChatSendRecord;
import com.bingo.pojo.po.im.BingoChatShow;
import com.bingo.service.ChatService;
import com.bingo.store.BingoChatSendRecordStore;
import com.bingo.store.BingoChatShowStore;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

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
    public void sendChatRecord(ChatRecordDTO msgDTO) {
        // 适配器模式：对象转换
        BingoChatSendRecord sendRecord = ChatRecordAdapter.buildChatRecordPO(msgDTO);
        Long uid = msgDTO.getUid();
        Long goalId = msgDTO.getGoalId();

        // 保存聊天记录
        CompletableFuture.runAsync(() -> {
            recordStore.saveChatRecord(sendRecord);
        }, taskExecutor);

        // 处理聊天会话窗口逻辑
        CompletableFuture showFuture = CompletableFuture.runAsync(() -> {
            BingoChatShow uRecord = showStore.getOneRecord(uid, goalId);
            BingoChatShow goalRecord = showStore.getOneRecord(goalId, uid);
            if (ObjectUtils.isEmpty(uRecord)) {
                showStore.saveRecord(uid, goalId);
            }
            if (ObjectUtils.isEmpty(goalRecord)) {
                showStore.saveRecord(goalId, uid);
            }
        }, taskExecutor);

        // 更新未读数量
        showFuture.thenRunAsync(() -> {
            BingoChatShow record = showStore.getOneRecord(uid, goalId);
            record.setUnreadCount(record.getUnreadCount() + 1);
            record.setReceiveTime(new Date());
            showStore.updateRecordById(record);
        }, taskExecutor);

        // 通过Channel发送消息
        kafkaTemplate.send(MQConstant.IM_SEND_TOPIC, sendRecord);
    }
}
