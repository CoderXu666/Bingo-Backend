package com.bingo.controller;

import com.bingo.enums.RespCodeEnum;
import com.bingo.pojo.common.PageParam;
import com.bingo.pojo.dto.LikeDTO;
import com.bingo.pojo.dto.PostDTO;
import com.bingo.pojo.dto.SearchDTO;
import com.bingo.pojo.vo.PostPageVO;
import com.bingo.pojo.vo.PostVO;
import com.bingo.resp.R;
import com.bingo.service.BingoPostService;
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
@RequestMapping("/post")
public class BingoPostController {
    @Autowired
    private BingoPostService postService;

    /**
     * 发布帖子
     */
    @PostMapping("/save")
    public R savePost(@RequestBody PostDTO postDTO) {
        postService.savePost(postDTO);
        return R.out(RespCodeEnum.SUCCESS, "操作成功");
    }

    /**
     * 根据关键字，搜索帖子
     */
    @PostMapping("/search")
    public R searchPost(@RequestBody SearchDTO searchDTO) throws IOException {
        List<PostVO> result = postService.searchPost(searchDTO);
        return R.out(RespCodeEnum.SUCCESS, result);
    }

    /**
     * 点赞帖子
     */
    @PostMapping("/like")
    public R likePost(@RequestBody LikeDTO likeDTO) {
        postService.likePost(likeDTO);
        return R.out(RespCodeEnum.SUCCESS, "操作成功");
    }

    /**
     * 展示用户最新的帖子（分页10条）
     */
    @PostMapping("/post_list")
    public R pagePost(@RequestBody PageParam pageParam) {
        PostPageVO postPageVO = postService.pagePost(pageParam);
        return R.out(RespCodeEnum.SUCCESS, postPageVO);
    }
}

