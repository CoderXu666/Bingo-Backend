package com.bingo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bingo.pojo.po.community.BingoFollowRecord;
import com.bingo.pojo.vo.user.UserVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户关注表 服务类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-08-23
 */
public interface BingoFollowRecordService extends IService<BingoFollowRecord> {
    Boolean followUser(Long uid, Long goalId);

    Map<String, Object> followList(Long uid, Integer current, Integer limit);

    Map<String, Object> fanList(Long goalId, Integer current, Integer limit);

    Map<String, Object> friendList(Long uid, Long goalId, Integer current, Integer limit);
}
