package com.bingo.store;


import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-07-13
 */
public interface BingoGroupInfoStore extends IService<BingoGroupInfo> {

    Boolean saveGroup(BingoGroupInfo groupChat);

    Boolean deleteGroup(Long id);

    Boolean updateGroup(BingoGroupInfo groupChat);
}
