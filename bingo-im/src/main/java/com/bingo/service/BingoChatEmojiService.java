package com.bingo.service;


import com.bingo.pojo.dto.im.EmojiDTO;
import com.bingo.pojo.po.im.BingoChatEmoji;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-07-18
 */
public interface BingoChatEmojiService {
    Boolean saveEmoji(EmojiDTO emojiDTO);

    Boolean deleteEmojiById(Long id);

    List<BingoChatEmoji> findEmojiByUserId(Long userId);
}
