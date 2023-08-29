package com.bingo.pojo.dto.community;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class TopicDTO {
    /**
     * 关联用户ID
     */
    @TableField("uid")
    private Long userId;

    /**
     * 话题内容
     */
    @TableField("topic_content")
    private String topicContent;

    /**
     * 话题图片
     */
    @TableField("topic_picture")
    private String topicPicture;

    /**
     * 话题标签
     */
    @TableField("topic_tag")
    private String topicTag;
}
