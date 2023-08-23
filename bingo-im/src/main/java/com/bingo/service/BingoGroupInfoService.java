package com.bingo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.bingo.pojo.dto.GroupInfoDTO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-07-13
 */
public interface BingoGroupInfoService extends IService<BingoGroupInfo> {

    Boolean saveGroup(GroupInfoDTO groupChatDTO);

    Boolean deleteGroup(Long id);

    Boolean updateGroup(GroupInfoDTO groupChatDTO);
}
