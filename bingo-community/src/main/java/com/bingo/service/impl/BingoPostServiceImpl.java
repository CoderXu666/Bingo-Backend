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
     * 注意：Redis里面没有 Long 类型
     */
    @Override
    public Boolean likePost(LikeDTO likeDTO) {
        Long postId = likeDTO.getPostId();
        String likeUserId = likeDTO.getLikeUserId();

        // 生成RedisKey（点赞记录、点赞次数）
        String likeRecordKey = postId + ":" + likeUserId;
        String likeCountKey = postId + ":count";

        // 帖子被用户点赞过，删除点赞记录，点赞总次数 - 1
        if (redisTemplate.hasKey(likeRecordKey)) {
            redisTemplate.delete(likeRecordKey);
            redisTemplate.opsForValue().decrement(likeCountKey, 1);
        }

        // 帖子没被用户点赞，保存点赞记录，点赞次数 + 1（需要判断是否是首赞）
        else {
            // 不是首赞用户，点赞总次数 + 1
            if (redisTemplate.hasKey(likeCountKey)) {
                redisTemplate.opsForValue().increment(likeCountKey, 1);
            } else {
                // 首赞用户，生成点赞总次数（默认为1）
                redisTemplate.opsForValue().set(likeCountKey, 1);
            }
            // 保存点赞记录
            redisTemplate.opsForValue().set(likeRecordKey, "YES");
        }

        // 消息发送给MQ，同步MySQL
//        kafkaProducer.sendMessage(MQTopicConstant.POST_LIKE, JSON.toJSONString());

        return null;
    }
}
