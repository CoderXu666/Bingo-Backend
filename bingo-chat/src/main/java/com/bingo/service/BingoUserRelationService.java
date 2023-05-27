package com.bingo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.bingo.pojo.po.BingoUserRelation;
import com.bingo.pojo.vo.BingoUserVO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-03-01
 */
public interface BingoUserRelationService extends IService<BingoUserRelation> {
    List<BingoUserVO> findFriend(Long id);
}
