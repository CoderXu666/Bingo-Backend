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
 * @since 2023-07-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bingo_group_chat")
public class BingoGroupChat implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 群聊ID（关联群聊ID）
     */
    @TableField("group_id")
    private Integer groupId;

    /**
     * 群聊类型（0：文字/图片、1：表情包、2：语音/视频）
     */
    @TableField("chat_type")
    private Boolean chatType;

    /**
     * 群聊内容
     */
    @TableField("chat_content")
    private String chatContent;

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
