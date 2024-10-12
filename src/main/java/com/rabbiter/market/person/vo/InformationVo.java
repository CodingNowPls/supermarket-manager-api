package com.rabbiter.market.person.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class InformationVo implements Serializable {
    /**
     *
     */
    private Long id;
    /**
     *
     */
    private String sex;
    /**
     *
     */
    private String username;
    /**
     *
     */
    private String nickName;
    /**
     *
     */
    private String headImg;
    /**
     *
     */
    private String info;
    /**
     *
     */
    private String address;
    /**
     *
     */
    private String email;
    /**
     *
     */
    private Integer age;
    /**
     *
     */
    private Long deptId;
    /**
     *
     */
    private String idCard;
    /**
     *
     */
    private String deptName;

}
