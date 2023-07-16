package com.bingo.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.BingoGroupChatMapper;
import com.bingo.pojo.dto.GroupChatDTO;
import com.bingo.pojo.po.BingoGroupInfo;
import com.bingo.service.BingoGroupChatService;
import com.bingo.store.BingoGroupChatStore;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 周英俊
 * @since 2023-07-13
 */
@Service
public class BingoGroupChatServiceImpl extends ServiceImpl<BingoGroupChatMapper, BingoGroupInfo> implements BingoGroupChatService {
    @Autowired
    private BingoGroupChatStore groupChatStore;

    /**
     * 创建群聊
     */
    @Override
    public Boolean saveGroup(GroupChatDTO groupChatDTO) {
        BingoGroupInfo groupChat = new BingoGroupInfo();
        BeanUtils.copyProperties(groupChatDTO, groupChat);
        return groupChatStore.saveGroup(groupChat);
    }

    /**
     * 删除群聊
     */
    @Override
    public Boolean deleteGroup(Long id) {
        return groupChatStore.deleteGroup(id);
    }

    /**
     * 更新群聊
     */
    @Override
    public Boolean updateGroup(GroupChatDTO groupChatDTO) {
        BingoGroupInfo groupChat = new BingoGroupInfo();
        groupChat.setUpdateTime(new Date());
        BeanUtils.copyProperties(groupChatDTO, groupChat);
        return groupChatStore.updateGroup(groupChat);
    }
}
