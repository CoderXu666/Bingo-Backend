package com.bingo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.bingo.pojo.po.BingoUser;
import com.bingo.pojo.vo.BingoUserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
     * 根据userId查询用户信息
     */
    BingoUserVO findById(Long id);

    /**
     * 生成验证码
     */
    void generateCode(HttpServletRequest request, HttpServletResponse response);

    /**
     * 修改用户信息
     */
    Boolean updateUser(BingoUser user);
}
