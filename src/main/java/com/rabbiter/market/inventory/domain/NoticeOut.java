package com.rabbiter.market.inventory.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class NoticeOut implements Serializable {
    /**
     *
     */
    private Long id;
    /**
     * 商品名
     */
    private String name;
    /**
     *
     */
    private Long goodsNum;
    /**
     * 商品封面
     */
    private String coverUrl;
    /**
     *
     */
    private String state;

}
