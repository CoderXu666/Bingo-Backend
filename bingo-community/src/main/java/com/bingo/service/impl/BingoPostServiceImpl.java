package com.bingo.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.constant.MQTopicConstant;
import com.bingo.kafka.KafkaProducer;
import com.bingo.mapper.BingoPostMapper;
import com.bingo.pojo.dto.LikeDTO;
import com.bingo.pojo.dto.PostDTO;
import com.bingo.pojo.po.BingoPost;
import com.bingo.service.BingoPostService;
import com.bingo.store.BingoPostStore;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-05-13
 */
@Service
public class BingoPostServiceImpl extends ServiceImpl<BingoPostMapper, BingoPost> implements BingoPostService {
    @Autowired
    private BingoPostStore postStore;
    @Autowired
    private KafkaProducer kafkaProducer;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 发布帖子
     */
    @Override
    public Boolean savePost(PostDTO postDTO) {
        // 保存 DB
        BingoPost bingoPost = new BingoPost();
        BeanUtils.copyProperties(postDTO, bingoPost);
        postStore.savePost(bingoPost);

        // 消息发送MQ，同步ES
        kafkaProducer.sendMessage(MQTopicConstant.COMMUNITY_POST_TOPIC, JSON.toJSONString(bingoPost));

        return true;
    }

    /**
     * 点赞帖子
     */
    @Override
    public Boolean likePost(LikeDTO likeDTO) {
        Long postId = likeDTO.getPostId();
        Long userId = likeDTO.getUserId();
        Long likeUserId = likeDTO.getLikeUserId();

        // 生成RedisKey（帖子赞数、点赞记录）
        String likeKey = "";
        String likeCountKey = "";

        // 消息发送给MQ，同步MySQL
//        kafkaProducer.sendMessage(MQTopicConstant.POST_LIKE, JSON.toJSONString());

        return null;
    }
}
