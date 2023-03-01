package com.bingo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.bingo.pojo.po.BingoUser;
import com.bingo.pojo.vo.BingoUserVO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-03-01
 */
public interface BingoUserService extends IService<BingoUser> {

    /**
     * 根据userName查询用户信息
     *
     * @param userName
     * @return
     */
    BingoUserVO findUserInfo(String userName);
}
