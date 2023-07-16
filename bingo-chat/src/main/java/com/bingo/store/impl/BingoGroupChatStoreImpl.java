package com.bingo.store.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.BingoGroupChatMapper;
import com.bingo.pojo.po.BingoGroupInfo;
import com.bingo.store.BingoGroupChatStore;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 周英俊
 * @since 2023-07-13
 */
@Service
public class BingoGroupChatStoreImpl extends ServiceImpl<BingoGroupChatMapper, BingoGroupInfo> implements BingoGroupChatStore {

    /**
     * 创建群聊
     */
    @Override
    public Boolean saveGroup(BingoGroupInfo groupChat) {
        return this.save(groupChat);
    }

    /**
     * 删除群聊
     */
    @Override
    public Boolean deleteGroup(Long id) {
        return this.removeById(id);
    }

    /**
     * 更新群聊
     */
    @Override
    public Boolean updateGroup(BingoGroupInfo groupChat) {
        return this.updateById(groupChat);
    }
}
