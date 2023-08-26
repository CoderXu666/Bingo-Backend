package com.bingo.store.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.BingoChatSendRecordMapper;
import com.bingo.pojo.po.im.BingoChatSendRecord;
import com.bingo.store.BingoChatSendRecordStore;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 好友聊天记录表 服务实现类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-08-23
 */
@Service
public class BingoChatSendRecordStoreImpl extends ServiceImpl<BingoChatSendRecordMapper, BingoChatSendRecord> implements BingoChatSendRecordStore {
    /**
     * 查看好友聊天消息
     */
    @Override
    public List<BingoChatSendRecord> getSendRecordList(Long userId, Long goalId) {
        return baseMapper.getSendRecordList(userId, goalId);
    }
}
