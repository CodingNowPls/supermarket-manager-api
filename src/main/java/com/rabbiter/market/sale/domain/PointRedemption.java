package com.rabbiter.market.sale.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 积分兑换历史记录
 */
@Data
@TableName("t_point_redemption")
public class PointRedemption implements Serializable {

    public static final String STATE_NORMAL = "0";

    public static final String STATE_DEL = "1";
    /**
     *
     */
    private String cn;
    /**
     *
     */
    @TableField("goods_id")
    private Long goodsId;
    /**
     *
     */
    @TableField("member_id")
    private Long memberId;
    /**
     *
     */
    private Long integral;
    /**
     *
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("update_time")
    private Date updateTime;
    /**
     *
     */
    private String updateby;
    /**
     *
     */
    @TableField("update_id")
    private Long updateId;
    /**
     *
     */
    private String state;
    /**
     * 会员账号
     */
    @TableField(exist = false)
    private String memberPhone;
    /**
     *
     */
    @TableField(exist = false)
    private String goodsName;
    /**
     *
     */
    @TableField(exist = false)
    private String goodsCoverUrl;

}
