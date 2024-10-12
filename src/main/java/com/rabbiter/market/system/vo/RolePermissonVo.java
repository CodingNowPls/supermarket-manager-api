package com.rabbiter.market.system.vo;


import com.fasterxml.jackson.annotation.JsonIgnoreType;
/**
 *
 */
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 *
 */
@Data
public class RolePermissonVo implements Serializable {
    /**
     *
     */
    private List<Long> menuIds;
    /**
     *
     */
    private List<RoleMenu> menus;

    public RoleMenu getRoleMenu() {
        return new RoleMenu();
    }
    /**
     * 成员内部类，供这个类使用
     */
    @Data
    @JsonIgnoreType
    public class RoleMenu {
        private Long value;
        private String label;
        private List<RoleMenu> children;

    }

}
