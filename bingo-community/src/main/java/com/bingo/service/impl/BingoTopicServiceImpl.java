package com.bingo.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.feign.UserFeign;
import com.bingo.mapper.BingoTopicMapper;
import com.bingo.pojo.dto.community.TopicDTO;
import com.bingo.pojo.po.community.BingoTopic;
import com.bingo.pojo.resp.community.BingoTopicResp;
import com.bingo.pojo.resp.user.UserResp;
import com.bingo.response.FeignResponse;
import com.bingo.service.BingoTopicService;
import com.bingo.store.BingoTopicStore;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class BingoTopicServiceImpl extends ServiceImpl<BingoTopicMapper, BingoTopic> implements BingoTopicService {
    @Autowired
    private BingoTopicStore topicStore;
    @Autowired
    private UserFeign userFeign;

    /**
     * 用户创建话题
     */
    @Override
    public Boolean saveTopic(TopicDTO topicDTO) throws Exception {
        if (!StringUtils.isNotEmpty(topicDTO.getUid().toString())) {
            throw new Exception("用户ID为空");
        }
        BingoTopic bingoTopic = new BingoTopic();
        BeanUtils.copyProperties(topicDTO, bingoTopic);
        return topicStore.saveTopic(bingoTopic);
    }

    /**
     * 用户删除话题
     */
    @Override
    public Boolean deleteTopic(Long id) {
        return topicStore.deleteTopic(id);
    }

    /**
     * 根据话题ID查询话题详细信息以及用户相关信息
     */
    @Override
    public BingoTopicResp getTopicById(Long topicId) throws Exception {
        if (ObjectUtils.isEmpty(topicId)) {
            throw new Exception("接口参数不存在");
        }

        //查询话题相关信息
        BingoTopic topic = topicStore.findTopicById(topicId);
        if (ObjectUtils.isEmpty(topic)) {
            throw new Exception("未查询到话题信息");
        }

        //远程调用查询用户相关信息
        FeignResponse<UserResp> feignResponse = userFeign.findByUserId(topic.getUid());
        UserResp userResp = feignResponse.getData();
        if (ObjectUtils.isEmpty(userResp)) {
            throw new Exception("未查询到该用户信息");
        }

        BingoTopicResp topicResp = new BingoTopicResp();
        BeanUtils.copyProperties(userResp, topicResp);
        BeanUtils.copyProperties(topic, topicResp);
        return topicResp;
    }
}
