package com.bingo.store.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.BingoFriendChatMapper;
import com.bingo.pojo.po.BingoFriendChat;
import com.bingo.store.BingoFriendChatStore;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 周英俊
 * @since 2023-07-16
 */
@Service
public class BingoFriendChatStoreImpl extends ServiceImpl<BingoFriendChatMapper, BingoFriendChat> implements BingoFriendChatStore {

    /**
     * 好友发送消息
     */
    @Override
    public Boolean saveFriendChat(BingoFriendChat bingoFriendChat) {
        return this.save(bingoFriendChat);
    }

    /**
     * 删除好友消息
     */
    @Override
    public Boolean deleteFriendChat(Long id) {
        return this.removeById(id);
    }


    /**
     * 查询好友聊天记录消息
     */
    @Override
    public List<BingoFriendChat> getContentsByRelation(String relation) {
        QueryWrapper<BingoFriendChat> wrapper = new QueryWrapper<>();
        wrapper.eq("relation", relation);
        wrapper.orderByAsc("create_time");
        return this.list(wrapper);
    }
}
