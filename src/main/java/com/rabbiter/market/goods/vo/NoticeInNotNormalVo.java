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
public class NoticeInNotNormalVo implements Serializable {

    private String cn;

    private String coverUrl;

    private Long goodsId;

    private Long untreatedNum;

    private String goodsName;

    private String state;

    private Long storeId;

    private String storeName;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;//通知时间

}
