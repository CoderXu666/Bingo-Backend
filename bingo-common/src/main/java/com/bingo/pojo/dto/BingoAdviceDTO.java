package com.bingo.pojo.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;


@Data
public class BingoAdviceDTO {
    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户建议
     */
    private String advice;

    /**
     * 分数（1~5）
     */
    private Integer score;
}
