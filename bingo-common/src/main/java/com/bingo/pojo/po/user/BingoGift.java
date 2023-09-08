package com.bingo.pojo.po.user;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 礼物权益表
 * </p>
 *
 * @author 徐志斌
 * @since 2023-09-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bingo_gift")
public class BingoGift implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 礼物名称
     */
    @TableField("gift_name")
    private String giftName;

    /**
     * 礼物图片
     */
    @TableField("gift_url")
    private String giftUrl;

    /**
     * 礼物价格
     */
    @TableField("gift_price")
    private BigDecimal giftPrice;

    /**
     * 礼物描述
     */
    @TableField("gift_desc")
    private String giftDesc;

    /**
     * 删除标识
     */
    @TableField("deleted")
    @TableLogic
    private Boolean deleted;


}
