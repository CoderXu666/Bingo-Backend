package com.bingo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.BingoFriendChatMapper;
import com.bingo.pojo.dto.FriendChatDTO;
import com.bingo.pojo.po.BingoFriendChat;
import com.bingo.pojo.po.BingoUserRelation;
import com.bingo.pojo.vo.ChatContentVO;
import com.bingo.service.BingoFriendChatService;
import com.bingo.store.BingoFriendChatStore;
import com.bingo.store.BingoUserRelationStore;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class BingoFriendChatServiceImpl extends ServiceImpl<BingoFriendChatMapper, BingoFriendChat> implements BingoFriendChatService {

    @Autowired
    private BingoFriendChatStore friendChatStore;
    @Autowired
    private BingoUserRelationStore relationStore;

    /**
     * 好友发送消息
     */
    @Override
    public Boolean saveFriendChat(FriendChatDTO friendChatDTO) throws Exception {
        Long userId1 = friendChatDTO.getUserId1();
        Long userId2 = friendChatDTO.getUserId2();
        Integer chatType = friendChatDTO.getChatType();
        String chatContent = friendChatDTO.getChatContent();

        // 校验双方是否是好友
        BingoUserRelation relation = relationStore.getOneRelationByTwoId(userId1, userId2);
        if (ObjectUtils.isEmpty(relation)) {
            throw new Exception("您还不是对方好友，发送信息失败");
        }

        // 保存好友聊天信息
        BingoFriendChat friendChat = new BingoFriendChat();
        friendChat.setUserId(userId1);
        friendChat.setChatType(chatType);
        friendChat.setChatContent(chatContent);
        friendChat.setRelation(relation.getRelation());
        return friendChatStore.saveFriendChat(friendChat);
    }

    /**
     * 删除好友消息
     */
    @Override
    public Boolean deleteFriendChat(Long id) {
        return friendChatStore.deleteFriendChat(id);
    }

    /**
     * 查询用户聊天记录
     */
    @Override
    public List<ChatContentVO> getChatContentByUserId(Long userId1, Long userId2) throws Exception {
        // 判断双方是否是好友
        BingoUserRelation relation = relationStore.getOneRelationByTwoId(userId1, userId2);
        if (ObjectUtils.isEmpty(relation)) {
            throw new Exception("对方已不是您的好友，聊天记录不存在");
        }

        // 查询聊天记录
        List<ChatContentVO> resultList = new ArrayList<>();
        List<BingoFriendChat> chatContents = friendChatStore.getContentsByRelation(relation.getRelation());
        for (BingoFriendChat chatContent : chatContents) {
            ChatContentVO chatContentVO = new ChatContentVO();
            chatContentVO.setUserId(chatContent.getUserId());
//            chatContentVO.setAvatarUrl();
//            chatContentVO.setNickName();
            chatContentVO.setChatContent(chatContent.getChatContent());
            chatContentVO.setCreateTime(chatContent.getCreateTime());
            resultList.add(chatContentVO);
        }

        return resultList;
    }
}
