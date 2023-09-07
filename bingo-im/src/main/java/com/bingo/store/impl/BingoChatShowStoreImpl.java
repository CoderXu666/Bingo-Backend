package com.bingo.store.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
     * 查询进行过聊天的好友关联关系
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
    public BingoChatShow getOneShowRecord(Long uid, Long goalId) {
        QueryWrapper<BingoChatShow> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", uid);
        wrapper.eq("goalId", goalId);
        return this.getOne(wrapper);
    }

    /**
     * 更新记录
     */
    @Override
    public Boolean updateShowRecord(BingoChatShow record) {
        return this.updateById(record);
    }
}
