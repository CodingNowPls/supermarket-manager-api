package com.rabbiter.market.person.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class EditEmpVo implements Serializable {
    /**
     *
     */
    private Long id;
    /**
     *
     */
    private String idCard;
    /**
     *
     */
    private String username;
    /**
     *
     */
    private String address;
    /**
     *
     */
    private String sex;
    /**
     *
     */
    private Integer age;
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
    private String state;
    /**
     *
     */
    private String info;
    /**
     *
     */
    private Long deptId;
    /**
     *
     */
    private String deptName;
    /**
     *
     */
    private Boolean isAdmin;


    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }
}
