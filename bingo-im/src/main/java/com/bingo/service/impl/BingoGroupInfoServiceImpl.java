package com.bingo.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.BingoGroupInfoMapper;
import com.bingo.pojo.dto.GroupInfoDTO;
import com.bingo.pojo.po.BingoGroupInfo;
import com.bingo.service.BingoGroupInfoService;
import com.bingo.store.BingoGroupInfoStore;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-07-13
 */
@Service
public class BingoGroupInfoServiceImpl extends ServiceImpl<BingoGroupInfoMapper, BingoGroupInfo> implements BingoGroupInfoService {
    @Autowired
    private BingoGroupInfoStore groupChatStore;

    /**
     * 创建群聊
     */
    @Override
    public Boolean saveGroup(GroupInfoDTO groupChatDTO) {
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
    public Boolean updateGroup(GroupInfoDTO groupChatDTO) {
        BingoGroupInfo groupChat = new BingoGroupInfo();
        groupChat.setUpdateTime(new Date());
        BeanUtils.copyProperties(groupChatDTO, groupChat);
        return groupChatStore.updateGroup(groupChat);
    }
}
