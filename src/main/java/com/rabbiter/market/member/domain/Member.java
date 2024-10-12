package com.rabbiter.market.member.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 *
 */
@Data
@TableName("t_member")
public class Member implements Serializable {
    public static final String STATE_NORMAL = "0";
    public static final String STATE_BAN = "1";
    public static final String DEFAULT_PWD = "123456";

    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     *
     */
    private String name;
    /**
     *
     */
    private String phone;
    /**
     *
     */
    @JsonIgnore
    private String password;
    /**
     *
     */
    private String email;
    /**
     *
     */
    private Long integral;
    /**
     *
     */
    private String state;
    /**
     *
     */
    private String info;

    public Member() {
    }

}
