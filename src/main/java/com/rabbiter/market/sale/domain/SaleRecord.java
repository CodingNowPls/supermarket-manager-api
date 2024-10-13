package com.rabbiter.market.sale.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@TableName("t_sale_record")
public class SaleRecord implements Serializable {

    public static final String STATE_NORMAL = "0";
    public static final String STATE_DEL = "1";
    private static final String TYPE_MEMBER = "1";
    private static final String TYPE_NOMEMBER = "0";
    private static final String SELLWAY_ALIPAY = "0";
    private static final String SELLWAY_WECHAT = "1";
    private static final String SELLWAY_CASH = "2";
    private static final String SELLWAY_CREDIT = "3";
    private static final Double DISCOUNT = 0.9;

    @TableField("cn")
    private String cn;

    private Long eid;

    private String sellway;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("sell_time")
    private Date sellTime;

    private String state;

    private String info;

    private String sellby;

    @TableField("sell_total")
    private Long sellTotal;

    @TableField("sell_totalmoney")
    private Double sellTotalmoney;

    private String type;

    @TableField(exist = false)
    private List<SaleRecordDetail> saleRecords = new ArrayList<>();

    //会员账号
    @TableField("member_phone")
    private String memberPhone;


}
