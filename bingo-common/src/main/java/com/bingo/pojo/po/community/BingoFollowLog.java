package com.bingo.pojo.po.community;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 关注操作日志
 * </p>
 *
 * @author 徐志斌
 * @since 2023-08-25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("bingo_follow_log")
public class BingoFollowLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 目标id
     */
    @TableField("goal_id")
    private Long goalId;

    /**
     * 操作（ADD，DELETE）
     */
    @TableField("operation")
    private String operation;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;


}
