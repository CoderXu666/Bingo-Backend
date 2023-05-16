package com.bingo.kafka;

import com.alibaba.fastjson.JSON;
import com.bingo.constant.ESConstant;
import com.bingo.constant.KafkaConstant;
import com.bingo.pojo.dto.BingoPostDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private RestHighLevelClient elasticsearchClient;

    /**
     * 监听帖子
     */
    @KafkaListener(topics = KafkaConstant.COMMUNITY_POST, groupId = KafkaConstant.GROUP_ID)
    public Boolean listenPostMsg(String message) throws IOException {
        BingoPostDTO post = JSON.parseObject(message, BingoPostDTO.class);
        IndexRequest request = new IndexRequest(ESConstant.POST_INDEX);
        request.source(new ObjectMapper().writeValueAsString(post), XContentType.JSON);
        IndexResponse response = elasticsearchClient.index(request, RequestOptions.DEFAULT);
        return response.getResult() == IndexResponse.Result.CREATED;
    }
}
