package com.bingo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.BingoChatFriendRecordMapper;
import com.bingo.pojo.dto.FriendChatDTO;
import com.bingo.pojo.po.BingoChatFriendRecord;
import com.bingo.service.BingoChatFriendRecordService;
import com.bingo.store.BingoChatFriendRecordStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-07-16
 */
@Service
public class BingoChatFriendRecordServiceImpl extends ServiceImpl<BingoChatFriendRecordMapper, BingoChatFriendRecord> implements BingoChatFriendRecordService {

    @Autowired
    private BingoChatFriendRecordStore friendChatStore;

    /**
     * 好友发送消息
     */
    @Override
    public Boolean saveFriendChat(FriendChatDTO friendChatDTO) throws Exception {
        return friendChatStore.saveFriendChat(null);
    }

    /**
     * 删除好友消息
     */
    @Override
    public Boolean recallMessage(FriendChatDTO chatDTO) throws Exception {
        return friendChatStore.recallMessage(null, null);
    }
}
