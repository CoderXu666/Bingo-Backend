package com.bingo.controller;


import com.bingo.enums.RespCodeEnum;
import com.bingo.pojo.dto.community.TopicDTO;
import com.bingo.response.R;
import com.bingo.pojo.resp.community.BingoTopicResp;
import com.bingo.service.BingoTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 徐志斌
 * @since 2023-07-01
 */
@RestController
@RequestMapping("/topic")
public class BingoTopicController {
    @Autowired
    private BingoTopicService topicService;

    /**
     * 当前热门话题查询
     */
    @PostMapping("/topic_list")
    public R hotTopic() {
        return null;
    }

    /**
     * 用户创建话题
     */
    @PostMapping("/save_topic")
    public R saveTopic(@RequestBody TopicDTO topicDTO) {
        topicService.saveTopic(topicDTO);
        return R.out(RespCodeEnum.SUCCESS, "保存成功");
    }

    /**
     * 用户删除话题
     */
    @DeleteMapping("/delete_topic")
    public R deleteTopic(Long id) {
        topicService.deleteTopic(id);
        return R.out(RespCodeEnum.SUCCESS, "删除成功");
    }

    /**
     * 根据话题ID查询话题详细信息以及用户相关信息
     */
    @GetMapping("/information")
    public R getTopicById(Long topicId) throws Exception {
        BingoTopicResp topicResp = topicService.getTopicById(topicId);
        return R.out(RespCodeEnum.SUCCESS, topicResp);
    }


}

