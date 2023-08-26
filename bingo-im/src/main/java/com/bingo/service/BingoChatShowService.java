package com.bingo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bingo.pojo.po.im.BingoChatSendRecord;
import com.bingo.pojo.po.im.BingoChatShow;
import com.bingo.pojo.vo.im.ChatShowVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 聊天窗口列表（展示） 服务类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-08-24
 */
public interface BingoChatShowService extends IService<BingoChatShow> {

    Map<String, List<BingoChatSendRecord>> getChatList(Long userId);
}
