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
    /**
     * 通用
     */
    SUCCESS(200, "操作成功"),
    FAIL(500, "操作失败"),
    NO_STRATEGY_HANDLER(50002, "Map中不存在当前策略类"),


    /**
     * 自定义
     */
    /*---------------------------------USER模块------------------------------*/
    TOKEN_EXPIRED(50001, "用户Token已失效，请重新登录"),
    USER_NOT_EXIST(50002, "未查询到当前用户相关信息"),
    ;

    @Getter
    private Integer code;
    @Getter
    private String msg;
}