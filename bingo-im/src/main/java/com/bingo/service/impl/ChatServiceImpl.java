package com.bingo.service.impl;

import com.alibaba.fastjson.JSON;
import com.bingo.adapter.ChatRecordAdapter;
import com.bingo.constant.MQConstant;
import com.bingo.kafka.KafkaProducer;
import com.bingo.pojo.dto.im.ChatRecordDTO;
import com.bingo.pojo.po.im.BingoChatSendRecord;
import com.bingo.pojo.po.im.BingoChatShow;
import com.bingo.service.ChatService;
import com.bingo.store.BingoChatSendRecordStore;
import com.bingo.store.BingoChatShowStore;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
@Slf4j
@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    private KafkaProducer producer;
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

        // 保存聊天记录（保存成功，才算发送成功）
        recordStore.saveChatRecord(sendRecord);

        // 处理聊天会话窗口逻辑 | 更新未读数量
        CompletableFuture.runAsync(() -> {
            BingoChatShow uRecord = showStore.getOneRecord(uid, goalId);
            BingoChatShow goalRecord = showStore.getOneRecord(goalId, uid);
            if (ObjectUtils.isEmpty(uRecord)) {
                showStore.saveRecord(uid, goalId);
            }
            if (ObjectUtils.isEmpty(goalRecord)) {
                showStore.saveRecord(goalId, uid);
            }
            BingoChatShow record = showStore.getOneRecord(uid, goalId);
            record.setUnreadCount(record.getUnreadCount() + 1);
            record.setReceiveTime(new Date());
            showStore.updateRecordById(record);
        }, taskExecutor);

        // 通过Channel发送消息
        producer.sendMessage(MQConstant.IM_SEND_TOPIC, JSON.toJSONString(sendRecord));
    }
}
