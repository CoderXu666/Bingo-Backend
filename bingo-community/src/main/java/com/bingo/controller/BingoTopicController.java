package com.bingo.controller;


import com.bingo.enums.RespCodeEnum;
import com.bingo.pojo.dto.TopicDTO;
import com.bingo.pojo.resp.R;
import com.bingo.service.BingoTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 周英俊
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
    @PostMapping("/delete_topic")
    public R deleteTopic(String id) {
        topicService.deleteTopic(id);
        return R.out(RespCodeEnum.SUCCESS, "删除成功");
    }

}

