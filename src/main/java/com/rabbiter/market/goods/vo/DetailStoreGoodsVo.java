package com.rabbiter.market.goods.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 *
 */
@Data
public class DetailStoreGoodsVo implements Serializable {

    private String cn;

    private Long goodsId;

    private Long goodsNum;

    private String goodsName;

    private Double goodsPrice;

    private String info;

    private Long createid;

    private String createby;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expiryTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthTime;

    private String state1;

    private Long storeId;

    private String storeName;

    private String state;

    private Long supplierId;

    private String supplierName;


}
