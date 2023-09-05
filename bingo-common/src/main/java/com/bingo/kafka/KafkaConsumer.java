package com.bingo.kafka;

import com.alibaba.fastjson.JSON;
import com.bingo.constant.ESConstant;
import com.bingo.constant.MQConstant;
import com.bingo.pojo.dto.im.ChatMsgDTO;
import com.bingo.pojo.po.community.BingoPost;
import com.bingo.pojo.po.im.BingoChatSendRecord;
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
     * 保存帖子信息到ES
     */
    @KafkaListener(topics = MQConstant.COMMUNITY_POST_TOPIC, groupId = MQConstant.POST_GROUP_ID)
    public String savePost(String message) {
        try {
            BingoPost bingoPost = JSON.parseObject(message, BingoPost.class);
            IndexRequest request = new IndexRequest(ESConstant.POST_INDEX)
                    .id(bingoPost.getId().toString())
                    .source(JSON.toJSONString(bingoPost), XContentType.JSON);
            IndexResponse response = esClient.index(request, RequestOptions.DEFAULT);
            return response.getId();
        } catch (Exception e) {
            log.error("保存帖子ES数据报错：{}", e.getMessage());
            return null;
        }
    }

    /**
     * 点赞帖子
     */
    @KafkaListener(topics = MQConstant.POST_LIKE_TOPIC, groupId = MQConstant.POST_GROUP_ID)
    public String likePost(String message) {
        return null;
    }

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
