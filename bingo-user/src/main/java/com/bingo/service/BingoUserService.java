package com.bingo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.bingo.pojo.dto.user.UserDTO;
import com.bingo.pojo.po.user.BingoUser;
import com.bingo.pojo.vo.user.BingoUserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-03-01
 */
public interface BingoUserService extends IService<BingoUser> {
    BingoUserVO findById(Long id);

    void generateCode(HttpServletRequest request, HttpServletResponse response);

    Boolean updateUser(BingoUser user);

    List<BingoUserVO> getUserByIds(List<Long> ids);

    Boolean register(UserDTO userDTO) throws Exception;

    String login(UserDTO userDTO) throws Exception;

    void sendEmail(String email) throws Exception;

    BingoUser resolveToken(String token) throws Exception;
}
