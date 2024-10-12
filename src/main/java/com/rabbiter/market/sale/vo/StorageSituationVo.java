package com.rabbiter.market.sale.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 仓库存储情况
 */
@Data
public class StorageSituationVo implements Serializable {
    /**
     *
     */
    private Long storeId;
    /**
     *
     */
    private String storeName;
    /**
     * 该仓库存储商品数量
     */
    private Long residueNum;

}
