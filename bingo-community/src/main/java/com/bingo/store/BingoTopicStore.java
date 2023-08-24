package com.bingo.store;


import com.baomidou.mybatisplus.extension.service.IService;
import com.bingo.pojo.po.community.BingoTopic;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-07-01
 */
public interface BingoTopicStore extends IService<BingoTopic> {

    Boolean saveTopic(BingoTopic Topic);

    Boolean deleteTopic(Long id);

    BingoTopic findTopicById(Long topicId);
}
