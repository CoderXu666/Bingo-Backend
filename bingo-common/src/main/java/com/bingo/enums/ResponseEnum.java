package com.bingo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author 徐志斌
 * @Date: 2023/5/8 22:00
 * @Version 1.0
 * @Description: 响应封装类状态码 + 响应信息
 */
@Getter
@AllArgsConstructor
public enum ResponseEnum {
    /**
     * 通用
     */
    SUCCESS(200, "操作成功"),
    FAIL(500, "操作失败"),
    NO_ARGS(50, "参数信息不存在，系统出现异常"),
    NO_STRATEGY_HANDLER(50, "Map中不存在当前策略类"),
    FILE_NOT_EXIST(50, "当前文件不存在"),


    /**
     * 自定义
     */
    /*---------------------------------USER------------------------------*/
    TOKEN_EXPIRED(5000, "用户Token已失效，请重新登录"),
    TOKEN_NOT_EXIST(5000, "用户Token信息不存在"),

    USER_NOT_EXIST(5000, "未查询到当前用户相关信息"),
    USER_EXIST(5000, "当前账号已存在，换个试试"),
    USER_LONG(5000, "当前账号长度太长！"),
    USER_SHORT(5000, "当前账号长度太短！"),

    CAPTCHA_ERROR(5000, "验证码输入错误，请重试"),
    CAPTCHA_NOT_EXIST(5000, "验证码不存在，请刷新页面重新获取"),

    PASSWORD_ERROR(5000, "密码输入错误，请重试"),
    PASSWORD_DIFF(5000, "两次密码输入不一致，请重试"),
    PASSWORD_LONG(5000, "密码长度太长！"),
    PASSWORD_SHORT(5000, "密码长度太短！"),
    ;

    /*---------------------------------COMMUNITY------------------------------*/
    /*---------------------------------IM------------------------------*/
    /*---------------------------------PARTY------------------------------*/

    private Integer code;
    private String msg;
}