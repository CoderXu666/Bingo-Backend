package com.bingo.pojo.po.community;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户关注关联表
 * </p>
 *
 * @author 徐志斌
 * @since 2023-07-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bingo_follow_record")
public class BingoFollowRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 关注者
     */
    @TableField("user_id1")
    private Long userId1;

    /**
     * 被关注者
     */
    @TableField("user_id2")
    private Long userId2;

    /**
     * 最新消息时间（排序）
     */
    @TableField("receive_time")
    private Date receiveTime;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 删除标识
     */
    @TableField("deleted")
    @TableLogic
    private Boolean deleted;


}
