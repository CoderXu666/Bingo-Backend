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
    public void sendChatByUid(ChatRecordDTO msgDTO) {
        // 适配器模式：对象转换
        Long uid = msgDTO.getUid();
        Long goalId = msgDTO.getGoalId();
        BingoChatSendRecord sendRecord = ChatRecordAdapter.buildChatRecordPO(msgDTO);

        // 保存聊天记录
        CompletableFuture.runAsync(() -> {
            recordStore.saveChatRecord(sendRecord);
        }, taskExecutor);

        // 聊天会话处理
        CompletableFuture showFuture = CompletableFuture.runAsync(() -> {
            BingoChatShow record1 = showStore.getOneRecord(uid, goalId);
            BingoChatShow record2 = showStore.getOneRecord(goalId, uid);
            if (ObjectUtils.isEmpty(record1)) {
                showStore.saveRecord(uid, goalId);
            }
            if (ObjectUtils.isEmpty(record2)) {
                showStore.saveRecord(goalId, uid);
            }
        }, taskExecutor);

        // 更新未读数量（showFuture执行完毕执行该方法）
        showFuture.thenRunAsync(() -> {
            BingoChatShow record = showStore.getOneRecord(msgDTO.getGoalId(), msgDTO.getUid());
            record.setUnreadCount(record.getUnreadCount() + 1);
            record.setReceiveTime(new Date());
            showStore.updateRecordById(record);
        }, taskExecutor);

        // 通过Channel发送消息
        kafkaTemplate.send(MQConstant.IM_SEND_TOPIC, sendRecord);
    }
}
