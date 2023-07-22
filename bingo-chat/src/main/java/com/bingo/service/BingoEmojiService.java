package com.bingo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.bingo.pojo.dto.EmojiDTO;
import com.bingo.pojo.po.BingoEmoji;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-07-18
 */
public interface BingoEmojiService {

    Boolean saveEmoji(EmojiDTO emojiDTO);

    Boolean deleteEmojiById(Long id);

    List findEmojiByUserId(Long userId);
}
