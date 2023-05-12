package com.bingo.pojo.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户建议表
 * </p>
 *
 * @author 徐志斌
 * @since 2023-03-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bingo_advice")
public class BingoAdvice implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户名
     */
    @TableField("user_name")
    private String userName;

    /**
     * 用户建议
     */
    @TableField("advice")
    private String advice;

    /**
     * 分数（1~5）
     */
    @TableField("score")
    private Integer score;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;

    /**
     * 删除标识(未删除:0 已删除:1)
     */
    @TableField("deleted")
    @TableLogic
    private Boolean deleted;


}
