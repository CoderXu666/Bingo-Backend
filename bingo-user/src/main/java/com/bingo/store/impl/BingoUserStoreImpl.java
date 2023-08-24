package com.bingo.store.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.BingoUserMapper;
import com.bingo.pojo.po.user.BingoUser;
import com.bingo.store.BingoUserStore;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-03-01
 */
@Service
public class BingoUserStoreImpl extends ServiceImpl<BingoUserMapper, BingoUser> implements BingoUserStore {
    /**
     * 根据Id查询用户信息
     */
    @Override
    public BingoUser findById(Long id) {
        QueryWrapper<BingoUser> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        return this.getOne(wrapper);
    }

    /**
     * 根据查询用户信息
     */
    @Override
    public BingoUser findByAccountId(String accountId) {
        QueryWrapper<BingoUser> wrapper = new QueryWrapper<>();
        wrapper.eq("account_id", accountId);
        return this.getOne(wrapper);
    }

    /**
     * 修改用户信息
     */
    @Override
    public Boolean updateUser(BingoUser user) {
        return this.updateById(user);
    }

    /**
     * 根据ids批量查询用户信息
     */
    @Override
    public List<BingoUser> getUserListByIds(List<Long> ids) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.in("id", ids);
        return this.list(wrapper);
    }
}
