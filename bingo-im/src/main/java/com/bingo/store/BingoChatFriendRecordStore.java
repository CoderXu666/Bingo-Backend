package com.bingo.store;


import com.baomidou.mybatisplus.extension.service.IService;
import com.bingo.pojo.po.BingoChatFriendRecord;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-07-16
 */
public interface BingoChatFriendRecordStore extends IService<BingoChatFriendRecord> {
    Boolean saveFriendChat(BingoChatFriendRecord BingoChatFriendRecord);

    Boolean recallMessage(String relation, Long userId);

    List<BingoChatFriendRecord> getContentsByRelation(String relation);
}
