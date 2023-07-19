package com.bingo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.bingo.pojo.dto.FriendChatDTO;
import com.bingo.pojo.po.BingoFriendChat;
import com.bingo.pojo.vo.ChatContentVO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 周英俊
 * @since 2023-07-16
 */
public interface BingoFriendChatService extends IService<BingoFriendChat> {
    Boolean saveFriendChat(FriendChatDTO friendChatDTO) throws Exception;

    Boolean deleteFriendChat(Long id);

    List<ChatContentVO> getChatContentByUserId(Long userId1, Long userId2) throws Exception;
}
