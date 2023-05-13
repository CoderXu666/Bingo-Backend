package com.bingo.store;


import com.baomidou.mybatisplus.extension.service.IService;
import com.bingo.pojo.po.BingoUser;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-03-01
 */
public interface BingoUserStore extends IService<BingoUser> {

    /**
     * 根据Id查询用户信息
     */
    BingoUser findById(Long id);

    /**
     * 修改
     */
    Boolean updateUser(BingoUser user);
}
