package com.bingo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.feign.UserFeign;
import com.bingo.mapper.BingoChatShowMapper;
import com.bingo.pojo.po.im.BingoChatShow;
import com.bingo.pojo.vo.im.ChatShowVO;
import com.bingo.pojo.vo.user.UserVO;
import com.bingo.service.BingoChatShowService;
import com.bingo.store.BingoChatShowStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 聊天窗口列表（展示） 服务实现类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-08-24
 */
@Service
public class BingoChatShowServiceImpl extends ServiceImpl<BingoChatShowMapper, BingoChatShow> implements BingoChatShowService {
    @Autowired
    private BingoChatShowStore showStore;
    @Autowired
    private UserFeign userFeign;

    /**
     * 查询聊天列表
     */
    @Override
    public List<ChatShowVO> getChatList(Long userId) {
        // 查询进行过聊天的好友id
        List<Long> chatShowIds = showStore.getChatShowList(userId);

        // 查询好友信息
        List<UserVO> userVOList = userFeign.getUserInfoByIds(chatShowIds).getData();

        // 查询好友群聊信息


        return null;
    }
}
