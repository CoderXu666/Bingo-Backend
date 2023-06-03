package com.bingo.pojo.dto;

import lombok.Data;

/**
 * @author 徐志斌
 * @since 2023-03-01
 */
@Data
public class LoginUserDTO {
    /**
     * 账号
     */
    private String userId;

    /**
     * 密码
     */
    private String passWord;

    /**
     * 验证码
     */
    private String captcha;
}
