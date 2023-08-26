package com.bingo.store.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.BingoChatGroupSendRecordMapper;
import com.bingo.pojo.po.im.BingoChatGroupSendRecord;
import com.bingo.store.BingoChatGroupSendRecordStore;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 聊天记录表（群聊） 服务实现类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-08-26
 */
@Service
public class BingoChatGroupSendRecordStoreImpl extends ServiceImpl<BingoChatGroupSendRecordMapper, BingoChatGroupSendRecord> implements BingoChatGroupSendRecordStore {
    /**
     * 查询群聊记录
     */
    @Override
    public List<BingoChatGroupSendRecord> getSendRecordList(Long id) {
        QueryWrapper<BingoChatGroupSendRecord> wrapper = new QueryWrapper<>();
        wrapper.eq("group_id", id);
        wrapper.orderByDesc("create_time");
        wrapper.select(" LIMIT 10 ");
        return null;
    }
}
