package com.bingo.pojo.dto;

import lombok.Data;

/**
 * @Author 徐志斌
 * @Date: 2023/6/4 20:24
 * @Version 1.0
 * @Description: PageDTO
 */
@Data
public class PageDTO {
    /**
     * 起始页
     */
    private Integer current;
    /**
     * 每页数据条数
     */
    private Integer limit;
}
