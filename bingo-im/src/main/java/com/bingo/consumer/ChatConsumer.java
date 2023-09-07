package com.bingo.consumer;

import com.alibaba.fastjson.JSON;
import com.bingo.constant.MQConstant;
import com.bingo.pojo.dto.im.ChatMsgDTO;
import com.bingo.store.BingoChatSendRecordStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private BingoChatSendRecordStore recordStore;

    /**
     * 发送聊天消息
     */
    @KafkaListener(topics = MQConstant.IM_SEND_MSG_TOPIC, groupId = MQConstant.POST_GROUP_ID)
    public String sendMsg(String message) {
        ChatMsgDTO msgDTO = JSON.parseObject(message, ChatMsgDTO.class);
//        chatSendRecord.saveRecord();
        return null;
    }
}
