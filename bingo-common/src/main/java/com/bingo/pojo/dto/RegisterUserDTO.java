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
public class RegisterUserDTO {
    /**
     * 账号
     */
    private String userId;

    /**
     * 密码
     */
    private String passWord;

    /**
     * 邮箱
     */
    private String address;
}
