package com.rabbiter.market.goods.vo;

import lombok.Data;

import java.io.Serializable;

/**
 *
 */
@Data
public class GoodsStoreVo implements Serializable {
    private Long id;
    /**
     * 商品名
     */
    private String name;
    /**
     * 商品封面
     */
    private String coverUrl;
    /**
     * 需库存量
     */
    private Long inventory;
    /**
     * 货架商品数量
     */
    private Long shelves;

}
