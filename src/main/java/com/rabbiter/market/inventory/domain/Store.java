package com.rabbiter.market.inventory.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 仓库实体
 */
@Data
@TableName("store")
public class Store implements Serializable {
    public static final String STATE_NORMAL = "0";
    public static final String STATE_BAN = "-1";

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private String address;

    private String info;

    private String state;

}
