package com.bingo.pojo.dto;

import lombok.Data;

@Data
public class GroupInfoDTO {

    /**
     * ID
     */
    private Long id;

    /**
     * 群主ID
     */
    private Long userId;

    /**
     * 群名
     */
    private String groupName;

    /**
     * 群头像
     */
    private String avatarUrl;

    /**
     * 群成员
     */
    private String groupMember;

    /**
     * 群公告
     */
    private String groupNotice;

}
