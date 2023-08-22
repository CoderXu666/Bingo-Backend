package com.bingo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.BingoChatFriendRecordMapper;
import com.bingo.pojo.dto.FriendChatDTO;
import com.bingo.pojo.po.BingoChatFriend;
import com.bingo.pojo.po.BingoChatFriendRecord;
import com.bingo.service.BingoChatFriendRecordService;
import com.bingo.store.BingoChatFriendRecordStore;
import com.bingo.store.BingoChatFriendStore;
import org.apache.commons.lang3.ObjectUtils;
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
    @Autowired
    private BingoChatFriendStore relationStore;

    /**
     * 好友发送消息
     */
    @Override
    public Boolean saveFriendChat(FriendChatDTO friendChatDTO) throws Exception {
        // 发送者、接收者、消息类型、消息内容
        Long userId1 = friendChatDTO.getUserId1();
        Long userId2 = friendChatDTO.getUserId2();
        Integer chatType = friendChatDTO.getChatType();
        String chatContent = friendChatDTO.getChatContent();

        // 校验双方是否是好友
        BingoChatFriend relation = relationStore.getRelationByTwoId(userId1, userId2);
        if (ObjectUtils.isEmpty(relation)) {
            throw new Exception("您还不是对方好友，发送信息失败");
        }

        // 保存好友聊天信息
        BingoChatFriendRecord friendChat = new BingoChatFriendRecord();
        friendChat.setUserId(userId1);
        friendChat.setChatType(chatType);
        friendChat.setChatContent(chatContent);
        return friendChatStore.saveFriendChat(friendChat);
    }

    /**
     * 删除好友消息
     */
    @Override
    public Boolean recallMessage(FriendChatDTO chatDTO) throws Exception {
        // 发送者、接收者
        Long userId1 = chatDTO.getUserId1();
        Long userId2 = chatDTO.getUserId2();

        // 查询双方是否是好友
        BingoChatFriend relation = relationStore.getRelationByTwoId(userId1, userId2);
        if (ObjectUtils.isEmpty(relation)) {
            throw new Exception("对方不是您的好友");
        }

        // 删除消息
        return friendChatStore.recallMessage(null, userId1);
    }
}
