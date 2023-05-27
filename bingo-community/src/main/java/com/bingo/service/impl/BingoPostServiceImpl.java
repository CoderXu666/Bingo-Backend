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
import org.apache.commons.lang3.StringUtils;
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
        Long likeUserId = likeDTO.getLikeUserId();

        // 生成RedisKey（点赞状态、点赞次数）
        String likeStatusKey = postId + ":" + likeUserId;
        String likeCountKey = postId + ":count";

        // 查询点赞状态、点赞次数
        String likeStatus = (String) redisTemplate.opsForValue().get(likeStatusKey);
        String likeCount = (String) redisTemplate.opsForValue().get(likeCountKey);

        // 处理点赞状态
        if (StringUtils.isNotEmpty(likeStatus)) {
            if ("0".equals(likeStatus)) {
                redisTemplate.opsForValue().set(likeStatusKey, "1");
            } else {
                redisTemplate.opsForValue().set(likeStatusKey, "0");
            }
        } else {
            redisTemplate.opsForValue().set(likeStatusKey, "1");
        }

        /// 处理点赞数量
        if (StringUtils.isNotEmpty(likeCount)) {
            String likeCountVal = (String) redisTemplate.opsForValue().get(likeCount);
            redisTemplate.opsForValue().set(likeCountKey, likeCountVal + 1);
        } else {
            redisTemplate.opsForValue().set(likeCountKey, 1);
        }

        // 消息发送给MQ，同步MySQL
//        kafkaProducer.sendMessage(MQTopicConstant.POST_LIKE, JSON.toJSONString());


        return null;
    }
}
