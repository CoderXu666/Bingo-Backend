package com.bingo.controller;


import com.bingo.enums.RespCodeEnum;
import com.bingo.pojo.dto.EmojiDTO;
import com.bingo.pojo.po.BingoChatEmoji;
import com.bingo.pojo.resp.R;
import com.bingo.service.BingoChatEmojiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 徐志斌
 * @since 2023-07-18
 */
@RestController
@RequestMapping("/emoji")
public class BingoChatEmojiController {
    @Autowired
    private BingoChatEmojiService emojiService;

    /**
     * 保存一張表情包
     */
    @PostMapping("/save")
    public R saveEmoji(@RequestBody EmojiDTO emojiDTO) {
        emojiService.saveEmoji(emojiDTO);
        return R.out(RespCodeEnum.SUCCESS, "添加成功");
    }

    /**
     * 刪除一張表情包
     */
    @DeleteMapping("/delete")
    public R deleteEmojiById(Long id) {
        emojiService.deleteEmojiById(id);
        return R.out(RespCodeEnum.SUCCESS, "刪除成功");
    }

    /**
     * 表情包展示（按照最新时间排序）
     */
    @GetMapping("/find")
    public R findEmojiByUserId(Long userId) {
        List<BingoChatEmoji> emojiUrl = emojiService.findEmojiByUserId(userId);
        return R.out(RespCodeEnum.SUCCESS, emojiUrl);
    }
}

