package com.bingo.store.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.BingoTopicMapper;
import com.bingo.pojo.po.community.BingoTopic;
import com.bingo.store.BingoTopicStore;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-07-01
 */
@Service
public class BingoTopicStoreImpl extends ServiceImpl<BingoTopicMapper, BingoTopic> implements BingoTopicStore {

    /**
     * 用户创建话题
     */
    @Override
    public Boolean saveTopic(BingoTopic topic) {
        return this.save(topic);
    }

    /**
     * 用户删除话题
     */
    @Override
    public Boolean deleteTopic(Long id) {
        return this.removeById(id);
    }

    /**
     * 根据话题ID查询话题详细信息
     */
    @Override
    public BingoTopic findTopicById(Long topicId) {
        BingoTopic topic = this.getById(topicId);
        return topic;
    }
}
