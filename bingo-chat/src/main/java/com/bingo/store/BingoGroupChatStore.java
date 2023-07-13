package com.bingo.store;


import com.baomidou.mybatisplus.extension.service.IService;
import com.bingo.pojo.po.BingoGroupChat;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 周英俊
 * @since 2023-07-13
 */
public interface BingoGroupChatStore extends IService<BingoGroupChat> {

    Boolean saveGroup(BingoGroupChat groupChat);

    Boolean deleteGroup(Long id);

    Boolean updateGroup(BingoGroupChat groupChat);
}
