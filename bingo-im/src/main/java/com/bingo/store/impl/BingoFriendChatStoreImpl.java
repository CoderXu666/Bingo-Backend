package com.bingo.store.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.BingoFriendChatMapper;
import com.bingo.pojo.po.BingoChatFriendRecord;
import com.bingo.store.BingoFriendChatStore;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-07-16
 */
@Service
public class BingoFriendChatStoreImpl extends ServiceImpl<BingoFriendChatMapper, BingoChatFriendRecord> implements BingoFriendChatStore {

    /**
     * 好友发送消息
     */
    @Override
    public Boolean saveFriendChat(BingoChatFriendRecord bingoFriendChat) {
        return this.save(bingoFriendChat);
    }

    /**
     * 删除好友消息
     */
    @Override
    public Boolean recallMessage(String relation, Long userId) {
        UpdateWrapper<BingoChatFriendRecord> wrapper = new UpdateWrapper<>();
        wrapper.eq("relation", relation);
        wrapper.eq("user_id", userId);
        return this.remove(wrapper);
    }


    /**
     * 查询好友聊天记录消息
     */
    @Override
    public List<BingoChatFriendRecord> getContentsByRelation(String relation) {
        QueryWrapper<BingoChatFriendRecord> wrapper = new QueryWrapper<>();
        wrapper.eq("relation", relation);
        wrapper.orderByAsc("create_time");
        return this.list(wrapper);
    }
}
