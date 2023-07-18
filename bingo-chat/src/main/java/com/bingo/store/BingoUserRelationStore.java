package com.bingo.store;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bingo.pojo.po.BingoUserRelation;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-05-27
 */
public interface BingoUserRelationStore extends IService<BingoUserRelation> {
    List<BingoUserRelation> getRelationsById(Long userId);

    BingoUserRelation getOneRelationByTwoId(Long userId1, Long userId2);

    Boolean deleteById(Long userId, Long friendId) throws Exception;
}
