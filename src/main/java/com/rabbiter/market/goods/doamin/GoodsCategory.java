package com.rabbiter.market.goods.doamin;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 商品分类
 */
@Data
@TableName("goods_category")
public class GoodsCategory implements Serializable {

    public static final String STATE_NORMAL = "0";

    public static final String STATE_BAN = "-1";
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     *
     */
    private String name;
    /**
     *
     */
    private String info;
    /**
     *
     */
    private String state;

}