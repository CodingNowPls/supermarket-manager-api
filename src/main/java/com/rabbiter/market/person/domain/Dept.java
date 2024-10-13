package com.rabbiter.market.person.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 部门实体
 */
@Data
@TableName("t_department")
public class Dept implements Serializable {
    //正常
    public static final String STATE_NORMAL = "0";
    //禁用
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
