package com.bingo.store.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.BingoEmojiMapper;
import com.bingo.pojo.po.im.BingoChatEmoji;
import com.bingo.store.BingoChatEmojiStore;
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
public class BingoEmojiStoreImpl extends ServiceImpl<BingoEmojiMapper, BingoChatEmoji> implements BingoChatEmojiStore {

    /**
     * 保存一張表情包
     */
    @Override
    public Boolean saveEmoji(BingoChatEmoji bingoEmoji) {
        return this.save(bingoEmoji);
    }

    /**
     * 刪除一張表情包
     */
    @Override
    public Boolean deleteEmojiById(Long id) {
        return this.removeById(id);
    }

    /**
     * 表情包展示（按照最新时间排序）
     */
    @Override
    public List findEmojiByUserId(Long userId) {
        QueryWrapper<BingoChatEmoji> queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", userId);
        queryWrapper.orderByDesc("create_time");
        return this.list(queryWrapper);
    }
}
