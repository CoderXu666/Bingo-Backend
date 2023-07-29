package com.bingo.store;


import com.baomidou.mybatisplus.extension.service.IService;
import com.bingo.pojo.po.BingoFriendChat;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-07-16
 */
public interface BingoFriendChatStore extends IService<BingoFriendChat> {
    Boolean saveFriendChat(BingoFriendChat bingoFriendChat);

    Boolean recallMessage(String relation, Long userId);

    List<BingoFriendChat> getContentsByRelation(String relation);
}
