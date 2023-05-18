package com.bingo.kafka;

import com.bingo.constant.ESConstant;
import com.bingo.constant.KafkaConstant;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author 徐志斌
 * @Date: 2023/5/13 21:22
 * @Version 1.0
 * @Description: KafkaConsumer
 */
@Slf4j
@Component
public class KafkaConsumer {
    @Autowired
    private RestHighLevelClient esClient;

    /**
     * 监听帖子
     */
    @KafkaListener(topics = KafkaConstant.COMMUNITY_POST, groupId = KafkaConstant.GROUP_ID)
    public Boolean listenPostMsg(String message) throws IOException {
        try {
            IndexRequest request = new IndexRequest(ESConstant.POST_INDEX);
            request.source(message, XContentType.JSON);
            IndexResponse response = esClient.index(request, RequestOptions.DEFAULT);
            return response.getResult() == IndexResponse.Result.CREATED;
        } catch (Exception e) {
            log.error("保存帖子ES数据报错：{}", e.getMessage());
            return null;
        } finally {
            esClient.close();
        }
    }
}
