package com.rabbiter.market.goods.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 */
@Data
public class GoodsListVo implements Serializable {

    /*商品编码*/
    private Long id;
    /*商品封面*/
    private String coverUrl;

    /*商品名称*/
    private String name;

    /*售价*/
    private Double sellPrice;

    /*批发价*/
    private Double purchashPrice;

    /*商品数量*/
    private Long residueNum;

    /*可用库存*/
    private Long residueStoreNum;

    /*商品类型*/
    private Long categoryId;


    private String categoryName;

    /*状态，下架、上架*/
    private String state;
    /*操作者*/
    private String updateby;

    private String info;

    /*操作时间*/
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;

}
