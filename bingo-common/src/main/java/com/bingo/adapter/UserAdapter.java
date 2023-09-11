package com.bingo.adapter;

import com.bingo.pojo.dto.user.UserDTO;
import com.bingo.pojo.po.user.BingoUser;
import com.bingo.utils.AESUtil;
import org.springframework.beans.BeanUtils;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-09-11  14:41
 * @Description: 适配器模式：BingoUser
 * @Version: 1.0
 */
public class UserAdapter {
    public static BingoUser buildUserPO(Long uid, Integer status) {
        return BingoUser.builder()
                .uid(uid)
                .onlineStatus(status)
                .build();
    }

    public static BingoUser buildUserPO(UserDTO userDTO, String province, String city) {
        BingoUser userInfo = BingoUser.builder().build();
        BeanUtils.copyProperties(userDTO, userInfo);
        userInfo.setPassWord(AESUtil.encrypt(userDTO.getPassWord()));
        userInfo.setLocation(province + "-" + city);
        return userInfo;
    }
}
