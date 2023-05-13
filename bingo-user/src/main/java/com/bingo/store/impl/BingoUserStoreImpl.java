package com.bingo.store.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.BingoUserMapper;
import com.bingo.pojo.po.BingoUser;
import com.bingo.store.BingoUserStore;
import org.springframework.stereotype.Service;

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
     * 根据userId查询用户信息
     */
    @Override
    public BingoUser findByUserId(Long userId) {
        QueryWrapper<BingoUser> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        return this.getOne(wrapper);
    }

    /**
     * 修改用户信息
     */
    @Override
    public Boolean updateUser(BingoUser user) {
        return this.updateById(user);
    }
}
