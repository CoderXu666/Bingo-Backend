package com.bingo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.bingo.pojo.dto.FriendChatDTO;
import com.bingo.pojo.po.BingoChatFriendRecord;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-07-16
 */
public interface BingoChatFriendRecordService extends IService<BingoChatFriendRecord> {
    Boolean saveFriendChat(FriendChatDTO chatDTO) throws Exception;

    Boolean recallMessage(FriendChatDTO chatDTO) throws Exception;
}
