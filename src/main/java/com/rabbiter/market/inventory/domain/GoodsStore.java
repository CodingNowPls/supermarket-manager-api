package com.rabbiter.market.inventory.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("t_goods_store")
public class GoodsStore implements Serializable {
    /**
     *
     */
    @TableField("goods_id")
    private Long goodsId;
    /**
     *
     */
    @TableField("store_id")
    private Long storeId;
    /**
     *
     */
    @TableField("in_num")
    private Long inNum;
    /**
     *
     */
    @TableField("residue_num")
    private Long residueNum;
    /**
     *
     */
    @TableField("store_name")
    private String storeName;

}
