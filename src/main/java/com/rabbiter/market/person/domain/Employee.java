package com.rabbiter.market.person.domain;

import com.rabbiter.market.system.domain.Menu;
import com.rabbiter.market.system.domain.Role;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.*;

/**
 * 员工实体类
 */
@Data
@TableName("t_employee")
public class Employee implements Serializable {

    public static final String STATE_NORMAL = "0";
    public static final String STATE_DEL = "1";
    public static final String DEFAULT_PWD = "123456";
    public static final String SEX_MEN = "1";
    public static final String SEX_WOWEN = "0";
    public static final String DEFAULT_HEAD_IMG = "/dev-api/files/1694434162457_07.jpg";
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     *
     */
    private String sex;
    /**
     *
     */
    @TableField("isAdmin")
    private Boolean isAdmin;
    /**
     *
     */
    @TableField("phone")
    private String username;
    /**
     *
     */
    @TableField("nick_name")
    private String nickName;
    /**
     *
     */
    private String password;
    /**
     *
     */
    @TableField("head_img")
    private String headImg = DEFAULT_HEAD_IMG;
    /**
     *
     */
    private String state = STATE_NORMAL;
    /**
     *
     */
    private String info;
    /**
     *
     */
    private String createby;
    /**
     *
     */
    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    /**
     *
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField("leave_time")
    private Date leaveTime;
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
    @TableField("deptId")
    private Long deptId;
    /**
     *
     */
    @TableField(value = "dept_name", exist = false)
    private String deptName;
    /**
     * 角色集合
     */
    @TableField(exist = false)
    @JsonIgnore
    private Set<Role> roles;
    /**
     * 权限集合
     */
    @TableField(exist = false)
    @JsonIgnore
    private List<Menu> menus;
    /**
     *
     */
    @TableField(exist = false)
    @JsonIgnore
    private Set<String> flags;
    /**
     *
     */
    @TableField("id_card")
    private String idCard;


    public void setMenus(List<Menu> menus) {
        this.menus = menus;
        if (menus != null) {
            this.flags = getFlags(this.menus);
        }
    }

    /**
     * 获取权限标识符集合
     *
     * @param menus
     * @return
     */
    private Set<String> getFlags(List<Menu> menus) {
        Set<String> flags = new HashSet<>();
        for (Menu menu : menus) {
            //目录遍历
            if (menu.getFlag() != null) {
                flags.add(menu.getFlag());
                //如果没有子集
                if (menu.getChildren() == null) {
                    continue;
                }
                for (Menu child : menu.getChildren()) {
                    //菜单遍历
                    if (child.getFlag() != null) {
                        flags.add(child.getFlag());
                    }
                    //如果没有子集
                    if (child.getChildren() == null) {
                        continue;
                    }
                    for (Menu childChild : child.getChildren()) {
                        if (childChild.getFlag() != null) {
                            flags.add(childChild.getFlag());
                        }
                    }
                }
            }
        }
        return flags;
    }
}
