package com.bingo.consumer;

import com.alibaba.fastjson.JSON;
import com.bingo.constant.ESConstant;
import com.bingo.constant.MQConstant;
import com.bingo.pojo.po.community.BingoDynamic;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-09-07  13:23
 * @Description: Kafka消费者：帖子
 * @Version: 1.0
 */
@Slf4j
@Component
public class CommunityConsumer {
    @Autowired
    private RestHighLevelClient esClient;

    /**
     * 同步帖子信息到ES
     */
    @KafkaListener(topics = MQConstant.COMMUNITY_POST_TOPIC, groupId = MQConstant.COMMUNITY_GROUP_ID)
    public String savePost(String message) {
        try {
            BingoDynamic bingoPost = JSON.parseObject(message, BingoDynamic.class);
            IndexRequest request = new IndexRequest(ESConstant.POST_INDEX)
                    .id(bingoPost.getId().toString())
                    .source(JSON.toJSONString(bingoPost), XContentType.JSON);
            IndexResponse response = esClient.index(request, RequestOptions.DEFAULT);
            return response.getId();
        } catch (Exception e) {
            log.error("========================保存帖子ES数据报错：{}========================", e.getMessage());
            return null;
        }
    }
}
