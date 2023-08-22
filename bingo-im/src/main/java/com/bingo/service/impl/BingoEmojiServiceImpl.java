package com.bingo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.BingoEmojiMapper;
import com.bingo.pojo.dto.EmojiDTO;
import com.bingo.pojo.po.BingoChatEmoji;
import com.bingo.service.BingoChatEmojiService;
import com.bingo.store.BingoChatEmojiStore;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-07-18
 */
@Service
public class BingoEmojiServiceImpl extends ServiceImpl<BingoEmojiMapper, BingoChatEmoji> implements BingoChatEmojiService {
    @Autowired
    BingoChatEmojiStore bingoEmojiStore;

    /**
     * 保存一張表情包
     */
    @Override
    public Boolean saveEmoji(EmojiDTO emojiDTO) {
        BingoChatEmoji bingoEmoji = new BingoChatEmoji();
        BeanUtils.copyProperties(emojiDTO, bingoEmoji);
        return bingoEmojiStore.saveEmoji(bingoEmoji);
    }

    /**
     * 刪除一張表情包
     */
    @Override
    public Boolean deleteEmojiById(Long id) {
        return bingoEmojiStore.deleteEmojiById(id);
    }

    /**
     * 表情包展示（按照最新时间排序）
     */
    @Override
    public List<BingoChatEmoji> findEmojiByUserId(Long userId) {
        return bingoEmojiStore.findEmojiByUserId(userId);
    }
}
