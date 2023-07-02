package com.bingo.store.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.BingoTopicMapper;
import com.bingo.pojo.dto.TopicDTO;
import com.bingo.pojo.po.BingoTopic;
import com.bingo.store.BingoTopicStore;
import org.springframework.beans.BeanUtils;
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
    public Boolean saveTopic(TopicDTO topicDTO) {
        BingoTopic bingoTopic = new BingoTopic();
        BeanUtils.copyProperties(topicDTO, bingoTopic);
        boolean isSuccess = this.save(bingoTopic);
        return isSuccess;
    }

    /**
     * 用户删除话题
     */
    @Override
    public Boolean deleteTopic(String id) {
        boolean isSuccess = this.removeById(id);
        return isSuccess;
    }
}
