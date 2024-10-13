package com.rabbiter.market.sale.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 销售记录详细
 */
@Data
@TableName("t_sale_record_detail")
public class SaleRecordDetail implements Serializable {
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
