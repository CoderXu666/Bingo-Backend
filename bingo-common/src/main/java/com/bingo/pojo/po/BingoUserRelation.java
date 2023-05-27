package com.bingo.pojo.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 徐志斌
 * @since 2023-05-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bingo_user_relation")
public class BingoUserRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户id1
     */
    @TableField("user1")
    private Long userId1;

    /**
     * 用户id2
     */
    @TableField("user2")
    private Long userId2;

    /**
     * 备注（用户1给用户2的备注）
     */
    @TableField("user_alias1")
    private String userAlias1;

    /**
     * 备注（用户2给用户1的备注）
     */
    @TableField("user_alias2")
    private String userAlias2;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 删除标识
     */
    @TableField("deleted")
    @TableLogic
    private Boolean deleted;


}
