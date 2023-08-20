package com.bingo.store.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.BingoGroupInfoMapper;
import com.bingo.pojo.po.BingoGroupInfo;
import com.bingo.store.BingoGroupInfoStore;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-07-13
 */
@Service
public class BingoGroupInfoStoreImpl extends ServiceImpl<BingoGroupInfoMapper, BingoGroupInfo> implements BingoGroupInfoStore {

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
