package com.bingo.pojo.vo.im;

import com.bingo.pojo.po.im.BingoChatSendRecord;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author 徐志斌
 * @Date: 2023/8/24 22:31
 * @Version 1.0
 * @Description: 聊天列表显示
 */
@Data
public class ChatShowVO {
    /**
     * 账号
     */
    private String accountId;

    /**
     * 用户名
     */
    private String nickName;

    /**
     * 头像
     */
    private String avatarUrl;

    /**
     * 生日
     */
    private Date birthDay;

    /**
     * 性别(男:1 女:0)
     */
    private Integer gender;

    /**
     * 地址
     */
    private String address;

    /**
     * 聊天记录
     */
    private List<BingoChatSendRecord> chatContentList;
}
