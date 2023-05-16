package com.bingo.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * @Author 徐志斌
 * @Date: 2023/5/13 21:21
 * @Version 1.0
 * @Description: KafkaProducer
 */
@Slf4j
@Component
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * 发送消息
     */
    public void sendMessage(String topic, String message) {
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onSuccess(SendResult<String, Object> result) {
                log.info("消息发送成功：{}", result.getRecordMetadata().toString());
            }

            @Override
            public void onFailure(Throwable ex) {
                log.info("消息发送失败：{}", ex.getMessage());
            }
        });
    }
}
