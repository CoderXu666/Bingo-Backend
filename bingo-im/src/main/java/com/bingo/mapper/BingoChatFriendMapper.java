package com.bingo.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author 徐志斌
 * @since 2023-05-27
 */
public interface BingoChatFriendMapper extends BaseMapper<BingoChatFriend> {
    /**
     * 根据user_id查询当前用户的好友
     */
    @Select(
            "SELECT user_id1 FROM bingo_chat_friend WHERE user_id2 = #{id}" +
            " UNION ALL " +
            "SELECT user_id2 FROM bingo_chat_friend WHERE user_id1 = #{id}"
    )
    List<Long> getFriendsById(@Param("id") Long id);
}
