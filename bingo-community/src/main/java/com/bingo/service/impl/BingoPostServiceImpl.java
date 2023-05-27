package com.bingo.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.constant.KafkaConstant;
import com.bingo.kafka.KafkaProducer;
import com.bingo.mapper.BingoPostMapper;
import com.bingo.pojo.dto.PostDTO;
import com.bingo.pojo.po.BingoPost;
import com.bingo.service.BingoPostService;
import com.bingo.store.BingoPostStore;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    /**
     * 发布帖子
     */
    @Override
    public Boolean savePost(PostDTO postDTO) {
        // 保存 DB
        BingoPost bingoPost = new BingoPost();
        BeanUtils.copyProperties(postDTO, bingoPost);
        postStore.savePost(bingoPost);

        // 将消息发送给 Kafka，异步同步 ES
        kafkaProducer.sendMessage(KafkaConstant.COMMUNITY_POST, JSON.toJSONString(bingoPost));

        return true;
    }
}
