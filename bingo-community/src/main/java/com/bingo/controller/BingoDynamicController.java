package com.bingo.controller;

import com.bingo.enums.ResponseEnum;
import com.bingo.pojo.dto.PageDTO;
import com.bingo.pojo.dto.SearchDTO;
import com.bingo.pojo.dto.community.LikeDTO;
import com.bingo.pojo.dto.community.PostDTO;
import com.bingo.pojo.resp.community.PostPageResp;
import com.bingo.pojo.resp.community.PostResp;
import com.bingo.response.R;
import com.bingo.service.BingoDynamicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 徐志斌
 * @since 2023-05-13
 */
@Slf4j
@RestController
@RequestMapping("/dynamic")
public class BingoDynamicController {
    @Autowired
    private BingoDynamicService dynamicService;

    /**
     * 发布帖子
     */
    @PostMapping("/save")
    public R savePost(@RequestBody PostDTO postDTO) {
        dynamicService.saveDynamic(postDTO);
        return R.out(ResponseEnum.SUCCESS, null);
    }

    /**
     * 根据关键字，搜索帖子
     */
    @PostMapping("/search")
    public R searchPost(@RequestBody SearchDTO searchDTO) throws IOException {
        List<PostResp> result = dynamicService.searchDynamic(searchDTO);
        return R.out(ResponseEnum.SUCCESS, result);
    }

    /**
     * 点赞帖子
     */
    @PostMapping("/like")
    public R likePost(@RequestBody LikeDTO likeDTO) {
        dynamicService.likeDynamic(likeDTO);
        return R.out(ResponseEnum.SUCCESS, null);
    }

    /**
     * 展示用户最新的帖子（分页10条）
     */
    @PostMapping("/list")
    public R pagePost(@RequestBody PageDTO pageParam) {
        PostPageResp postPageResp = dynamicService.getList(pageParam);
        return R.out(ResponseEnum.SUCCESS, postPageResp);
    }
}

