package com.bingo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bingo.pojo.po.im.BingoChatSendRecord;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 好友聊天记录表 Mapper 接口
 * </p>
 *
 * @author 徐志斌
 * @since 2023-08-23
 */
public interface BingoChatSendRecordMapper extends BaseMapper<BingoChatSendRecord> {
    /**
     * 通过user_id查看聊天消息
     */
    @Select("SELECT\n" +
            "\t* \n" +
            "FROM\n" +
            "\tbingo_chat_send_record \n" +
            "WHERE\n" +
            "\tuesr_id = #{userId} \n" +
            "\tAND goal_id = #{goalId} UNION ALL\n" +
            "SELECT\n" +
            "\t* \n" +
            "FROM\n" +
            "\tbingo_chat_send_record \n" +
            "WHERE\n" +
            "\tuser_id = #{goal_id} \n" +
            "\tAND goal_id = #{user_id}")
    List<BingoChatSendRecord> getSendRecordList(@Param("userId") Long userId, @Param("goalId") Long goalId);
}
