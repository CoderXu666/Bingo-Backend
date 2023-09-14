package com.bingo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author 徐志斌
 * @Date: 2023/5/8 22:00
 * @Version 1.0
 * @Description: 响应封装类状态码 + 响应信息
 */
@AllArgsConstructor
public enum ResponseEnum {
    SUCCESS(200, "操作成功"),
    FAIL(500, "操作失败"),
    TOKEN_EXPIRED(501, "用户Token已失效，请重新登录");

    @Getter
    private Integer code;

    @Getter
    private String msg;
}