package com.rabbiter.market.goods.doamin;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 积分商品
 * @author gao
 */
@Data
@TableName("t_point_goods")
public class PointGoods implements Serializable {

    public static final String STATE_NORMAL = "0";

    public static final String STATE_DEL = "1";
    /**
     *
     */
    @TableField("goods_id")
    private Long goodsId;
    /**
     *
     */
    @TableField("goods_name")
    private String goodsName;
    /**
     *
     */
    private Long integral;
    /**
     *
     */
    private String updateby;
    /**
     *
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     *
     */
    @TableField("cover_url")
    private String coverUrl;
    /**
     *
     */
    @TableField("update_id")
    private Long updateId;
    /**
     *
     */
    private String state;

}
