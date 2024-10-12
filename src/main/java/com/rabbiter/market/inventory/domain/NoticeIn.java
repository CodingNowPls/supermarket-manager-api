package com.rabbiter.market.inventory.domain;


import lombok.Data;

import java.io.Serializable;

@Data
public class NoticeIn implements Serializable {
    private Long id;
    /**
     * 商品名
     */
    private String name;
    /**
     * 进货价格
     */
    private Double purchashPrice;
    private Long goodsNum;
    /**
     * 商品封面
     */
    private String coverUrl;

}
