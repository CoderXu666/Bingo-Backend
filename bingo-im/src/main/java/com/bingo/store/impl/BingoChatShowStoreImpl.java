package com.bingo.store.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.adapter.ChatShowAdapter;
import com.bingo.mapper.BingoChatShowMapper;
import com.bingo.pojo.po.im.BingoChatShow;
import com.bingo.store.BingoChatShowStore;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 聊天窗口列表（展示） 服务实现类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-08-24
 */
@Service
public class BingoChatShowStoreImpl extends ServiceImpl<BingoChatShowMapper, BingoChatShow> implements BingoChatShowStore {
    /**
     * 查询聊天会话id
     */
    @Override
    public List<BingoChatShow> getChatShowList(Long uid) {
        QueryWrapper<BingoChatShow> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", uid);
        wrapper.orderByDesc("receive_time");
        return this.list(wrapper);
    }

    /**
     * 根据uid，goalId精准查询
     */
    @Override
    public BingoChatShow getOneRecord(Long uid, Long goalId) {
        QueryWrapper<BingoChatShow> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", uid);
        wrapper.eq("goal_Id", goalId);
        return this.getOne(wrapper);
    }

    /**
     * 更新记录
     */
    @Override
    public Boolean updateRecordById(BingoChatShow record) {
        return this.updateById(record);
    }

    /**
     * 保存会话记录
     */
    @Override
    public Boolean saveRecord(Long uid, Long goalId) {
        return this.save(ChatShowAdapter.buildChatShowPO(uid, goalId));
    }
}
