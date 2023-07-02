package com.bingo.store;


import com.baomidou.mybatisplus.extension.service.IService;
import com.bingo.pojo.dto.TopicDTO;
import com.bingo.pojo.po.BingoTopic;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-07-01
 */
public interface BingoTopicStore extends IService<BingoTopic> {

    Boolean saveTopic(TopicDTO topicDTO);

    Boolean deleteTopic(String id);
}
