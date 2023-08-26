package com.bingo.store.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.BingoChatGroupMapper;
import com.bingo.pojo.po.im.BingoChatGroup;
import com.bingo.store.BingoChatGroupStore;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 群聊信息表 服务实现类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-08-26
 */
@Service
public class BingoChatGroupStoreImpl extends ServiceImpl<BingoChatGroupMapper, BingoChatGroup> implements BingoChatGroupStore {

    /**
     * 根据groupId集合，查询群组信息
     */
    @Override
    public List<BingoChatGroup> getGroupInfoByIds(List<Long> groupIds) {
        QueryWrapper<BingoChatGroup> wrapper = new QueryWrapper<>();
        wrapper.in("id", groupIds);
        return this.list(wrapper);
    }
}
