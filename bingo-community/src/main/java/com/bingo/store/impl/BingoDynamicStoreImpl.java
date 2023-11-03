package com.bingo.store.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.BingoDynamicMapper;
import com.bingo.pojo.dto.PageDTO;
import com.bingo.pojo.po.community.BingoDynamic;
import com.bingo.store.BingoDynamicStore;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-03-04
 */
@Service
public class BingoDynamicStoreImpl extends ServiceImpl<BingoDynamicMapper, BingoDynamic> implements BingoDynamicStore {
    /**
     * 保存帖子信息
     */
    @Override
    public boolean saveDynamic(BingoDynamic bingoPost) {
        return this.save(bingoPost);
    }

    /**
     * 展示用户最新的帖子（分页10条）
     */
    @Override
    public Page<BingoDynamic> pagePost(PageDTO pageParam) {
        QueryWrapper<BingoDynamic> queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc("create_time");
        Page<BingoDynamic> page = new Page<>(pageParam.getCurrent(), pageParam.getLimit());
        Page<BingoDynamic> postPage = this.page(page, queryWrapper);
        return postPage;
    }
}
