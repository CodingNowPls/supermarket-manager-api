package com.rabbiter.market.system.service;

import com.rabbiter.market.system.domain.Role;
import com.rabbiter.market.system.qo.RoleQuery;
import com.rabbiter.market.system.vo.RolePermissionVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IRoleService extends IService<Role> {
    /**
     * 查询该员工id的角色信息
     *
     * @param eid
     * @return
     */
    Set<Role> queryByEid(Long eid);

    /**
     * 条件查询角色信息
     *
     * @param qo
     * @return
     */
    List<Role> listByQo(RoleQuery qo);

    /**
     * 停用角色业务
     *
     * @param rid
     */
    void forbiddenRole(Long rid);

    /**
     * 保存角色信息业务
     *
     * @param role
     */
    void saveRole(Role role);

    /**
     * 查看角色权限的业务
     *
     * @param rid
     * @return
     */
    RolePermissionVo checkPermissons(Long rid);

    /**
     * 保存角色-菜单的关系业务
     *
     * @param rid
     * @param menuIds
     */
    void saveRolePermissions(Long rid, Long[] menuIds);

    List<Map<String, Object>> getRoleAll();

    List<Long> queryRoleIdsByEid(Long eid);

    void saveRoleEmp(Long eid, Long[] roleIds);

    void clearEmpPermission(Long id);
}
