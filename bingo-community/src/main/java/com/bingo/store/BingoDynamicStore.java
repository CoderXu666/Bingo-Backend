package com.bingo.store;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bingo.pojo.dto.PageDTO;
import com.bingo.pojo.po.community.BingoDynamic;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-03-04
 */
public interface BingoDynamicStore extends IService<BingoDynamic> {

    boolean saveDynamic(BingoDynamic bingoPost);

    Page<BingoDynamic> pagePost(PageDTO pageParam);
}
