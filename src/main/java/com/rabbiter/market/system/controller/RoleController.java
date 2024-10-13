package com.rabbiter.market.system.controller;

import com.rabbiter.market.common.exception.BusinessException;
import com.rabbiter.market.common.sercurity.annotation.HasPermission;
import com.rabbiter.market.common.web.response.JsonResult;
import com.rabbiter.market.system.domain.Role;
import com.rabbiter.market.system.qo.RoleQuery;
import com.rabbiter.market.system.service.IRoleService;
import com.rabbiter.market.system.vo.RolePermissionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * @author gao
 */
@RestController
@Validated
@RequestMapping("/system/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;

    /**
     * 查询角色所有的信息
     *
     * @return
     */
    @HasPermission("system:role:list")
    @PostMapping("/list")
    public JsonResult list(@RequestBody RoleQuery qo) {
        List<Role> roles = roleService.listByQo(qo);
        return JsonResult.success(roles);
    }

    /**
     * 停用角色
     *
     * @param rid
     * @return
     */
    @HasPermission("system:role:forbiddenRole")
    @PostMapping("/forbiddenRole")
    public JsonResult forbiddenRole(Long rid) {
        roleService.forbiddenRole(rid);
        return JsonResult.success();
    }

    /**
     * 修改角色信息
     *
     * @param role
     * @return
     */
    @HasPermission("system:role:edit_role")
    @PostMapping("/edit_role")
    public JsonResult edit_role(@RequestBody Role role) {
        if (Role.SYS_ID.equals(role.getId()) || 2L == role.getId()) {
            throw new BusinessException("不能停用系统拥有者");
        }
        if (role != null && StringUtils.hasText(role.getInfo())) {
            roleService.updateById(role);
        }
        return JsonResult.success();
    }

    /**
     * 保存角色信息
     *
     * @param role
     * @return
     */
    @HasPermission("system:role:save")
    @PostMapping("/save")
    public JsonResult save(@RequestBody Role role) {
        roleService.saveRole(role);
        return JsonResult.success();
    }

    /**
     * 查询角色拥有的权限
     *
     * @param rid
     * @return
     */
    @HasPermission("system:role:saveRolePermissons")
    @GetMapping("/checkPermissons")
    public JsonResult checkPermissons(@NotNull(message = "角色不能为空") Long rid) {
        RolePermissionVo vo = roleService.checkPermissons(rid);
        return JsonResult.success(vo);
    }

    /**
     * 保存角色-菜单的关系
     *
     * @param rid
     * @param menuIds
     * @return
     */
    @HasPermission("system:role:saveRolePermissons")
    @PostMapping("/saveRolePermissons")
    public JsonResult saveRolePermissons(@NotNull(message = "角色不能为空") @RequestParam("rid") Long rid, @RequestParam("menuIds") Long[] menuIds) {
        roleService.saveRolePermissons(rid, menuIds);

        return JsonResult.success();

    }

    @GetMapping("/all")
    public JsonResult getRoleAll() {
        List<Map<String, Object>> list = roleService.getRoleAll();
        return JsonResult.success(list);
    }

    @HasPermission("person:employee:queryRoleIdsByEid")
    @GetMapping("/queryRoleIdsByEid")
    public JsonResult queryRoleIdsByEid(Long eid) {
        List<Long> list = roleService.queryRoleIdsByEid(eid);
        return JsonResult.success(list);
    }

    @HasPermission("person:employee:queryRoleIdsByEid")
    @PostMapping("/saveRoleEmp")
    public JsonResult saveRoleEmp(Long eid, Long[] empRoleIds, HttpServletRequest request) {
        String token = request.getHeader("token");
        roleService.saveRoleEmp(eid, empRoleIds, token);
        return JsonResult.success();

    }
}
