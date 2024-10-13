package com.rabbiter.market.goods.doamin;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;


import java.io.Serializable;
import java.util.Date;

/**
 * 商品
 */
@Data
@TableName("t_goods")
public class Goods implements Serializable {
    public static final String STATE_UP = "0";
    public static final String STATE_DOWN = "1";

    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 商品名
     */
    private String name;
    /**
     *
     */
    private String info;
    /**
     * 商品分类名
     */
    @TableField("category_name")
    private String categoryName;
    /**
     * 商品分类id
     */
    @TableField("category_id")
    private Long categoryId;

    /**
     * 创建者
     */
    private String createby;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField("create_time")
    private Date createTime;

    /**
     * 更新者
     */
    private String updateby;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField("update_time")
    private Date updateTime;

    /**
     * 销售价格
     */
    @TableField("sell_price")
    private Double sellPrice;

    /**
     * 进货价格
     */
    @TableField("purchash_price")
    private Double purchashPrice;

    /**
     * 剩余数量
     */
    @TableField("residue_num")
    private Long residueNum;

    /**
     * 剩余库存数量
     */
    @TableField(exist = false)
    private Long residueStoreNum;

    /**
     * 商品封面
     */
    @TableField("cover_url")
    private String coverUrl;


    private String state = STATE_UP;
    /**
     * 销售量
     */
    @TableField("sales_volume")
    private Long salesVolume;

    /**
     * 需库存量
     */
    private Long inventory;
    /**
     * 货架商品数量
     */
    private Long shelves;

}
