package com.rabbiter.market.person.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 *
 */
@Data
public class DetailEmpVo implements Serializable {
    private Long id;
    private String sex;
    private Boolean isAdmin;
    private String username;
    private String nickName;
    private String password;
    private String headImg;
    private String state;
    private String info;
    private String createby;
    private String idCard;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date leaveTime;
    private String address;
    private String email;
    private Integer age;
    private Long deptId;
    private String deptName;
    //角色集合
    private Set<String> roleNames;

}
