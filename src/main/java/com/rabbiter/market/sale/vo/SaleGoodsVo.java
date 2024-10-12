package com.rabbiter.market.sale.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 单个商品销售信息
 */
@Data
public class SaleGoodsVo implements Serializable {
    /**
     *
     */
    private Long goodsId;
    /**
     *
     */
    private String goodsName;
    /**
     *
     */
    private String coverUrl;
    /**
     *
     */
    private Long salesVolume;//销量
    /**
     *
     */
    private Long percentage = 0L;

    public void setPercentage(Long total) {
        if (total == null || total == 0) {
            this.percentage = 0L;
        } else {
            if (this.salesVolume == null) {
                this.salesVolume = 0L;
            }
            String num = ((this.salesVolume * 100.0) / total) + "";
            Long num1 = Long.valueOf(num.split("\\.")[0]);
            this.percentage = num1;
        }

    }

}
