package com.bingo.store.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.BingoChatFriendRecordMapper;
import com.bingo.pojo.po.BingoChatFriendRecord;
import com.bingo.store.BingoChatFriendRecordStore;
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
public class BingoChatFriendRecordStoreImpl extends ServiceImpl<BingoChatFriendRecordMapper, BingoChatFriendRecord> implements BingoChatFriendRecordStore {

    /**
     * 好友发送消息
     */
    @Override
    public Boolean saveFriendChat(BingoChatFriendRecord BingoChatFriendRecord) {
        return this.save(BingoChatFriendRecord);
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
