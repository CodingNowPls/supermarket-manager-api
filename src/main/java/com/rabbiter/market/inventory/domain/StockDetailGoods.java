package com.rabbiter.market.inventory.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 *
 */
@Data
@TableName("t_detail_store_goods")
public class StockDetailGoods implements Serializable {
    public static final String STATE_NORMAL = "0"; //正常
    public static final String STATE_EXPIRY = "1"; //过期
    public static final String STATE_DOWN = "2"; //下架
    public static final String STATE1_DEL = "1"; //删除
    public static final String STATE1_NORMAL = "0"; //正常
    public static final String STATE1_UNTREATED = "2"; //待处理
    public static final String TYPE_IN = "0";
    public static final String TYPE_OUT = "1";
    /**
     *
     */
    private String cn;
    @TableField("goods_id")
    /**
     *
     */
    private Long goodsId;
    /**
     *
     */
    @TableField("goods_num")
    private Long goodsNum;
    /**
     *
     */
    @TableField("goods_name")
    private String goodsName;
    /**
     *
     */
    @TableField("goods_price")
    private Double goodsPrice;
    /**
     *
     */
    private String type;
    /**
     *
     */
    private Long createid;
    /**
     *
     */
    private String createby;
    /**
     *
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("create_time")
    private Date createTime;
    /**
     *
     */
    private String state;
    /**
     *
     */
    private String info;
    /**
     *
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("expiry_time")
    private Date expiryTime;
    /**
     *
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("birth_time")
    private Date birthTime;
    /**
     *
     */
    @TableField("store_id")
    private Long storeId;
    /**
     *
     */
    private String state1;
    /**
     *
     */
    @TableField("supplier_id")
    private Long supplierId;
    /**
     *
     */
    @TableField("supplier_name")
    private String supplierName;
    /**
     *
     */
    @TableField("untreated_num")
    private Long untreatedNum;

}
