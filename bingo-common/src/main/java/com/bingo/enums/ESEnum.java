package com.bingo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-09-27  15:07
 * @Description: ElasticSearch相关索引
 * @Version: 1.0
 * <p>
 */
@Getter
@AllArgsConstructor
public enum ESEnum {
    DYNAMIC_INDEX("community_dynamic_index", "社区动态ES索引");

    private String index;
    private String desc;
}
