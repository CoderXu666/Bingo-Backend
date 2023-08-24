package com.bingo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bingo.pojo.po.im.BingoChatShow;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 聊天窗口列表（展示） Mapper 接口
 * </p>
 *
 * @author 徐志斌
 * @since 2023-08-24
 */
public interface BingoChatShowMapper extends BaseMapper<BingoChatShow> {
    /**
     * 查询进行过聊天的好友
     */
    @Select("SELECT\n" +
            "\tuser_id1 \n" +
            "FROM\n" +
            "\tbingo_chat_show \n" +
            "WHERE\n" +
            "\tuser_id2 = #{userId} \n" +
            "ORDER BY\n" +
            "\tcreate_time DESC UNION ALL\n" +
            "SELECT\n" +
            "\tuser_id1 \n" +
            "FROM\n" +
            "\tbingo_chat_show \n" +
            "WHERE\n" +
            "\tuser_id2 = #{userId} \n" +
            "ORDER BY\n" +
            "\tcreate_time DESC")
    List<Long> getChatShowList(@Param("userId") Long userId);
}
