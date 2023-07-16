package com.bingo.store;


import com.baomidou.mybatisplus.extension.service.IService;
import com.bingo.pojo.po.BingoGroupInfo;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 周英俊
 * @since 2023-07-13
 */
public interface BingoGroupChatStore extends IService<BingoGroupInfo> {

    Boolean saveGroup(BingoGroupInfo groupChat);

    Boolean deleteGroup(Long id);

    Boolean updateGroup(BingoGroupInfo groupChat);
}
