package com.rabbiter.market.system.vo;


import com.fasterxml.jackson.annotation.JsonIgnoreType;
/**
 *
 */
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * permissions
 * @author gao
 */
@Data
public class RolePermissionVo implements Serializable {
    /**
     *
     */
    private List<Long> menuIds;
    /**
     *
     */
    private List<RoleMenu> menus;

    /**
     *
     */
    public RoleMenu getRoleMenu() {
        return new RoleMenu();
    }

    @Data
    @JsonIgnoreType
    public class RoleMenu {
        /**
         *
         */
        private Long value;
        /**
         *
         */
        private String label;
        /**
         *
         */
        private List<RoleMenu> children;

    }

}
