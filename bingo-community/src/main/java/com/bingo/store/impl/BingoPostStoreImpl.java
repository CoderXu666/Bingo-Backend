package com.bingo.store.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.BingoPostMapper;
import com.bingo.pojo.common.PageParam;
import com.bingo.pojo.po.BingoPost;
import com.bingo.store.BingoPostStore;
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
public class BingoPostStoreImpl extends ServiceImpl<BingoPostMapper, BingoPost> implements BingoPostStore {
    /**
     * 保存帖子信息
     */
    @Override
    public boolean savePost(BingoPost bingoPost) {
        return this.save(bingoPost);
    }

    /**
     * 展示用户最新的帖子（分页10条）
     *
     * @return
     */
    @Override
    public Page<BingoPost> pagePost(PageParam pageParam) {
        QueryWrapper<BingoPost> queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc("create_time");
        Page<BingoPost> page = new Page<>(pageParam.getCurrent(), pageParam.getLimit());
        Page<BingoPost> postPage = this.page(page, queryWrapper);
        return postPage;
    }
}
