package com.bingo.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @Author 徐志斌
 * @Date: 2023/5/13 21:22
 * @Version 1.0
 * @Description: KafkaConsumer
 */
@Component
public class KafkaConsumer {
    /**
     * 监听
     */
    @KafkaListener(topics = "my-topic", groupId = "my-group")
    public void consume(String message) {
        System.out.println("Received message: " + message);
    }
}
