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
 * @since 2023-06-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bingo_notice_log")
public class BingoNoticeLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 关联用户表主键ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 通知类型（1:系统通知，2:点赞通知，3:关注通知）
     */
    @TableField("notice_type")
    private Boolean noticeType;

    /**
     * 通知内容
     */
    @TableField("notice_content")
    private String noticeContent;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 删除标识（1：没删除，2：已删除）
     */
    @TableField("deleted")
    @TableLogic
    private Boolean deleted;


}
