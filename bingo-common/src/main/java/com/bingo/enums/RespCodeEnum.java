package com.bingo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author 徐志斌
 * @Date: 2023/5/8 22:00
 * @Version 1.0
 * @Description: RespCodeEnum
 */
@AllArgsConstructor
public enum RespCodeEnum {
    SUCCESS(200, "操作成功"),
    FAIL(500, "操作失败"),
    ;

    @Getter
    @Setter
    private Integer code;

    @Getter
    @Setter
    private String msg;
}