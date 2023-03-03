package com.bingo.constant;

import lombok.Data;

/**
 * @Author 徐志斌
 * SpringCache注解对应的缓存分区
 * <p>
 * 注意：优先作用于Redis，其次才是Caffeine
 */
@Data
public class CachePartition {
    public static final String BINGO_USER = "bingo_user" ;
    public static final String BINGO_USER_STATISTICS = "bingo_user_statistics" ;
}
