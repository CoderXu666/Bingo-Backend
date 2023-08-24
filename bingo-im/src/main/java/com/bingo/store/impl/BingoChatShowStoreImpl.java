package com.bingo.store.impl;

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
     * 查询进行过聊天的好友
     */
    @Override
    public List<Long> getChatShowList(Long userId) {
        return baseMapper.getChatShowList(userId);
    }
}
