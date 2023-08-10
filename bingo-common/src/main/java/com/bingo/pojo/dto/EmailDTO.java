package com.bingo.pojo.dto;

import lombok.Data;

/**
 * @Author 徐志斌
 * @Date: 2023/8/6 19:29
 * @Version 1.0
 * @Description: EmailDTO
 */
@Data
public class EmailDTO {
    /**
     * 接收人Email
     */
    private String toEmail;
    /**
     * 主题
     */
    private String subject;
}
