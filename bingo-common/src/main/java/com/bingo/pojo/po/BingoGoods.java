package com.bingo.pojo.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author 徐志斌
 * @since 2023-06-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bingo_goods")
public class BingoGoods implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 商品名
     */
    @TableField("goods_name")
    private String goodsName;

    /**
     * 商品价格
     */
    @TableField("goods_price")
    private BigDecimal goodsPrice;

    /**
     * 商品折扣价格
     */
    @TableField("goods_vip_price")
    private BigDecimal goodsVipPrice;

    /**
     * 商品描述
     */
    @TableField("goods_desc")
    private String goodsDesc;

    /**
     * 库存数量
     */
    @TableField("goods_count")
    private Integer goodsCount;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;

    /**
     * 删除标识
     */
    @TableField("deleted")
    @TableLogic
    private Boolean deleted;


}
