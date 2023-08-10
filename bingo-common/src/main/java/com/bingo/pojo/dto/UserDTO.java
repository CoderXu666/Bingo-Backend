package com.bingo.pojo.dto;


import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author 徐志斌
 * @since 2023-03-01
 */
@Data
public class UserDTO {
    /**
     * 账号id
     */
    private String accountId;

    /**
     * 密码
     */
    private String passWord;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 验证码
     */
    private String captcha;
}
