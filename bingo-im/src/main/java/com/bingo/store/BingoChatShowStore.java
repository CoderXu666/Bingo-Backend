package com.bingo.store;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bingo.pojo.po.im.BingoChatShow;

import java.util.List;

/**
 * <p>
 * 聊天窗口列表（展示） 服务类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-08-24
 */
public interface BingoChatShowStore extends IService<BingoChatShow> {

    List<BingoChatShow> getChatShowList(Long uid);

    BingoChatShow getOneShowRecord(Long uid, Long goalId);

    Boolean updateShowRecord(BingoChatShow record);
}
