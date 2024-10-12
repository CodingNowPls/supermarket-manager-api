package com.rabbiter.market.sale.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 *
 */
@Data
@TableName("detail_sale_records")
public class DetailSaleRecords implements Serializable {
    /**
     *
     */
    @TableField("sell_cn")
    private String sellCn;
    /**
     *
     */
    @TableField("goods_id")
    private Long goodsId;
    /**
     *
     */
    @TableField("goods_num")
    private Long goodsNum;
    /**
     *
     */
    @TableField("goods_price")
    private Double goodsPrice;
    /**
     *
     */
    @TableField("goods_name")
    private String goodsName;

}
