package com.bingo.store.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.BingoFriendChatMapper;
import com.bingo.pojo.po.BingoFriendChat;
import com.bingo.store.BingoFriendChatStore;
import org.springframework.stereotype.Service;

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
}
