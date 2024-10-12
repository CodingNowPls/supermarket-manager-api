package com.rabbiter.market.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 权限实体类
 */
@Data
@TableName("t_role")
public class Role implements Serializable {
    public static final Long SYS_ID = 1L;
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
