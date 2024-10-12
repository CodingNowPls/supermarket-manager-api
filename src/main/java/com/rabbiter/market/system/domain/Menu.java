package com.rabbiter.market.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 *
 */
@Data
@TableName("t_menu")
public class Menu implements Serializable {
    public static final String TYPE_CATALOGUE = "0";//目录
    public static final String TYPE_MENU = "1"; //菜单
    public static final String TYPE_BUTTON = "2";//按钮
    public static final String STATE_NORMAL = "0";//正常
    public static final String STATE_DEL = "-1";//禁用
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     *
     */
    private String label;
    /**
     *
     */
    private String purl;
    /**
     *
     */
    private String type;
    /**
     *
     */
    @TableField("parent_id")
    private Long parentId;
    /**
     *
     */
    @TableField("parent_label")
    private String parentLabel;
    /**
     *
     */
    private String info;
    /**
     *
     */
    private String state;
    /**
     *
     */
    private String flag;
    /**
     *
     */
    private String icon;
    /**
     *
     */
    private String component;
    /**
     *
     */
    @TableField(exist = false)
    private List<Menu> children;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return Objects.equals(id, menu.id) &&
                Objects.equals(label, menu.label) &&
                Objects.equals(purl, menu.purl) &&
                Objects.equals(type, menu.type) &&
                Objects.equals(parentId, menu.parentId) &&
                Objects.equals(parentLabel, menu.parentLabel) &&
                Objects.equals(info, menu.info) &&
                Objects.equals(state, menu.state) &&
                Objects.equals(flag, menu.flag) &&
                Objects.equals(component, menu.component) &&
                Objects.equals(children, menu.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label, purl, type, parentId, parentLabel, info, state, flag, children);
    }

}
