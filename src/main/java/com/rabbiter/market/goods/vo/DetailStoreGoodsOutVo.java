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
public class DetailStoreGoodsOutVo implements Serializable {

    private String cn;

    private Long goodsId;

    private Long goodsNum;

    private String goodsName;

    private Long createid;

    private String createby;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    private String state;

    private String info;

    private Long storeId;

    private String storeName;

    private String state1;

}

