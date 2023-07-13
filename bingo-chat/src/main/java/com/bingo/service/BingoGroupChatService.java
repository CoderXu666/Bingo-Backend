package com.bingo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.bingo.pojo.dto.GroupChatDTO;
import com.bingo.pojo.po.BingoGroupChat;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 周英俊
 * @since 2023-07-13
 */
public interface BingoGroupChatService extends IService<BingoGroupChat> {

    Boolean saveGroup(GroupChatDTO groupChatDTO);

    Boolean deleteGroup(Long id);

    Boolean updateGroup(GroupChatDTO groupChatDTO);
}
