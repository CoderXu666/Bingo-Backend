package com.bingo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.bingo.pojo.dto.community.TopicDTO;
import com.bingo.pojo.po.community.BingoTopic;
import com.bingo.pojo.vo.community.BingoTopicVO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-07-01
 */
public interface BingoTopicService extends IService<BingoTopic> {

    Boolean saveTopic(TopicDTO topicDTO);

    Boolean deleteTopic(Long id);

    BingoTopicVO getTopicById(Long topicId) throws Exception;
}
