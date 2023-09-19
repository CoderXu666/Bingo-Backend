package com.bingo.pojo.dto;

import com.bingo.pojo.PageParam;
import lombok.Data;

/**
 * @Author 徐志斌
 * @Date: 2023/6/4 20:25
 * @Version 1.0
 * @Description: SearchDTO
 */
@Data
public class SearchDTO extends PageParam {
    /**
     * 内容类型
     */
    private Integer type;
    /**
     * 输入框的内容
     */
    private String content;
}
