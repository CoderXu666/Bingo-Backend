package com.bingo.store;


import com.baomidou.mybatisplus.extension.service.IService;
import com.bingo.pojo.po.user.BingoUser;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-03-01
 */
public interface BingoUserStore extends IService<BingoUser> {
    BingoUser findById(Long id);

    BingoUser findByAccountId(String uid);

    Boolean updateUserById(BingoUser user);

    List<BingoUser> getUserListByIds(List<Long> ids);
}
