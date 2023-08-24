package com.bingo.store;


import com.baomidou.mybatisplus.extension.service.IService;
import com.bingo.pojo.po.im.BingoChatEmoji;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-07-18
 */
public interface BingoChatEmojiStore extends IService<BingoChatEmoji> {

    Boolean saveEmoji(BingoChatEmoji bingoEmoji);

    Boolean deleteEmojiById(Long id);

    List findEmojiByUserId(Long userId);
}
