package com.bingo.store.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.BingoChatSendRecordMapper;
import com.bingo.pojo.po.im.BingoChatSendRecord;
import com.bingo.store.BingoChatSendRecordStore;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        QueryWrapper<BingoChatSendRecord> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("uid", userId);
        wrapper1.eq("goal_id", goalId);
        List<BingoChatSendRecord> list1 = this.list(wrapper1);

        QueryWrapper<BingoChatSendRecord> wrapper2 = new QueryWrapper<>();
        wrapper2.eq("uid", goalId);
        wrapper2.eq("goal_id", userId);
        List<BingoChatSendRecord> list2 = this.list(wrapper2);

        List<BingoChatSendRecord> resultList = new ArrayList<>();
        resultList.addAll(list1);
        resultList.addAll(list2);
        return resultList;
    }

    /**
     * 保存聊天记录
     */
    @Override
    public Boolean saveChatRecord(BingoChatSendRecord record) {
        return this.save(record);
    }
}
