package com.bingo.pojo.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author 周英俊
 * @since 2023-07-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bingo_follow_relation")
public class BingoFollowRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户1（关联用户2主键ID）
     */
    @TableField("user_id1")
    private Long userId1;

    /**
     * 用户2（关联用户2主键ID）
     */
    @TableField("user_id2")
    private Long userId2;

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
