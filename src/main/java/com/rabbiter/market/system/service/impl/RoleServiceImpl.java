package com.rabbiter.market.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.rabbiter.market.common.exception.BusinessException;
import com.rabbiter.market.common.redis.service.RedisTemplateService;
import com.rabbiter.market.person.domain.Employee;
import com.rabbiter.market.system.domain.Menu;
import com.rabbiter.market.system.domain.Role;
import com.rabbiter.market.system.mapper.RoleMapper;
import com.rabbiter.market.system.qo.RoleQuery;
import com.rabbiter.market.person.service.IEmployeeService;
import com.rabbiter.market.system.service.IMenuService;
import com.rabbiter.market.system.service.IRoleService;
import com.rabbiter.market.system.vo.RolePermissionVo;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rabbiter.market.util.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private IMenuService menuService;
    @Autowired
    private RedisTemplateService redisTemplateService;
    @Autowired
    private IEmployeeService employeeService;

    @Override
    public Set<Role> queryByEid(Long eid) {
        Set<Role> roles = roleMapper.queryByEid(eid);
        return roles;
    }

    @Override
    public List<Role> listByQo(RoleQuery qo) {
        LambdaQueryWrapper<Role> wrapper = Wrappers.lambdaQuery(Role.class)
                .like(StringUtils.hasText(qo.getName()), Role::getName, qo.getName())
                .eq(StringUtils.hasText(qo.getState()), Role::getState, qo.getState())
                .ne(Role::getId, Role.SYS_ID)
                .ne(Role::getId, 2L);

        List<Role> list = super.list(wrapper);
        return list;
    }

    @Override
    public void forbiddenRole(Long rid) {
        Role role = super.getById(rid);
        if (Role.SYS_ID.equals(role.getId()) || 2L == role.getId()) {
            throw new BusinessException("不能停用系统拥有者");
        }
        UpdateWrapper<Role> wrapper = new UpdateWrapper<Role>()
                .set("state", Role.STATE_BAN)
                .eq("id", rid);
        super.update(wrapper);
    }

    @Override
    public void saveRole(Role role) {
        if (role == null) {
            throw new BusinessException("操作失败");
        }
        if (StringUtils.hasText(role.getName())) {
            //查询是否保存过
            Role role1 = super.getOne(Wrappers.lambdaQuery(Role.class)
                    .eq(Role::getName, role.getName()));
            if (role1 != null) {
                throw new BusinessException("操作失败，角色名重复");
            } else {
                role.setState(Role.STATE_NORMAL);
                super.save(role);
            }
        } else {
            throw new BusinessException("角色名称格式有误");
        }

    }

    @Transactional
    @Override
    public RolePermissionVo checkPermissons(Long rid) {
        RolePermissionVo vo = new RolePermissionVo();
        /*角色所拥有的菜单id集合*/
        List<Long> menuIds = new ArrayList<>();
        /*所有的菜单信息*/
        List<RolePermissionVo.RoleMenu> menus1 = new ArrayList<>();
        //步骤1：查询所有的菜单信息
        List<Menu> menus = menuService.findAll();
        if (menus == null) {
            throw new BusinessException("授权功能还待上线...");
        }
        //封装菜单信息
        for (Menu menu : menus) {
            RolePermissionVo.RoleMenu roleMenu_catalogs = vo.getRoleMenu();
            roleMenu_catalogs.setValue(menu.getId());
            roleMenu_catalogs.setLabel(menu.getLabel());
            /*目录下的菜单*/
            List<RolePermissionVo.RoleMenu> children = new ArrayList<>();
            //目录
            if (menu.getChildren() != null) {
                for (Menu child : menu.getChildren()) {
                    //菜单
                    RolePermissionVo.RoleMenu roleMenu_menu = vo.getRoleMenu();
                    roleMenu_menu.setValue(child.getId());
                    roleMenu_menu.setLabel(child.getLabel());
                    List<RolePermissionVo.RoleMenu> children1 = new ArrayList<>();
                    if (child.getChildren() != null) {
                        for (Menu childChild : child.getChildren()) {
                            //按钮
                            RolePermissionVo.RoleMenu roleMenu_button = vo.getRoleMenu();
                            roleMenu_button.setValue(childChild.getId());
                            roleMenu_button.setLabel(childChild.getLabel());
                            children1.add(roleMenu_button);
                        }
                        roleMenu_menu.setChildren(children1);
                    }
                    children.add(roleMenu_menu);
                }
                roleMenu_catalogs.setChildren(children);
            }
            menus1.add(roleMenu_catalogs);
        }
        vo.setMenus(menus1);

        //步骤2：是否是系统管理员
        if (rid.equals(Role.SYS_ID)) {
            //系统管理员
            //封装角色拥有的菜单id集合
            for (Menu menu : menus) {
                menuIds.add(menu.getId());
                //目录
                if (menu.getChildren() != null) {
                    for (Menu child : menu.getChildren()) {
                        //菜单
                        menuIds.add(child.getId());
                        if (child.getChildren() != null) {
                            for (Menu childChild : child.getChildren()) {
                                //按钮
                                menuIds.add(childChild.getId());
                            }
                        }

                    }
                }

            }
            vo.setMenuIds(menuIds);
        } else {
            //非系统管理员
            menuIds = roleMapper.getMenuIdByRid(rid);
            vo.setMenuIds(menuIds);
        }
        return vo;
    }

    @Transactional
    @Override
    public void saveRolePermissions(Long rid, Long[] menuIds) {
        //判断是否是系统管理员
        if (rid.equals(Role.SYS_ID) || rid == 2L) {
            //系统管理员
            throw new BusinessException("系统管理员的权限不可操作");
        } else {
            //非系统管理员
            //重新建立关系
            if (menuIds == null || menuIds.length == 0) {
                return;
            } else {
                List<Long> ids = new ArrayList<>();
                for (Long menuId : menuIds) {
                    ids.add(menuId);
                }

                Set<Long> totalIds = new HashSet<>();
                LambdaQueryWrapper<Menu> qoWrapper = Wrappers.lambdaQuery(Menu.class)
                        .in(Menu::getId, ids);
                List<Menu> list1 = menuService.list(qoWrapper);
                for (Menu menu : list1) {
                    //按钮
                    if (menu.getParentId() != null && Menu.TYPE_BUTTON.equals(menu.getType())) {
                        totalIds.add(menu.getId());
                        LambdaQueryWrapper<Menu> btnWrapper = Wrappers.lambdaQuery(Menu.class)
                                .eq(Menu::getId, menu.getParentId())
                                .eq(Menu::getType, Menu.TYPE_MENU);
                        Menu menu1 = menuService.getOne(btnWrapper);
                        totalIds.add(menu1.getId());
                        totalIds.add(menu1.getParentId());
                    } else if (menu.getParentId() != null && Menu.TYPE_MENU.equals(menu.getType())) {
                        //菜单
                        totalIds.add(menu.getId());
                        totalIds.add(menu.getParentId());
                    } else if (menu.getParentId() == null && Menu.TYPE_CATALOGUE.equals(menu.getType())) {
                        //目录
                        totalIds.add(menu.getId());
                    }
                }
                //清除关系
                roleMapper.clearRecordsByRid(rid);
                List<Map<String, Object>> list = new ArrayList<>();
                for (Long id : totalIds) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("rid", rid);
                    map.put("mid", id);
                    list.add(map);
                }
                roleMapper.saveRolePermissions(list);
            }
        }

    }

    @Override
    public List<Map<String, Object>> getRoleAll() {
        List<Map<String, Object>> list = new ArrayList<>();
        LambdaQueryWrapper<Role> wrapper = Wrappers.lambdaQuery(Role.class)
                .eq(Role::getState, Role.STATE_NORMAL)
                .ne(Role::getId, Role.SYS_ID)
                .ne(Role::getId, 2L);
        List<Role> roles = super.list(wrapper);
        for (Role role : roles) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", role.getId());
            map.put("label", role.getName());
            list.add(map);
        }
        return list;
    }

    @Override
    public List<Long> queryRoleIdsByEid(Long eid) {
        Employee emp = employeeService.getById(eid);
        if (emp.getIsAdmin()) {
            return roleMapper.queryRoleIdsAll();
        } else {
            return roleMapper.queryRoleIdsByEid(eid);
        }
    }

    @Override
    public void saveRoleEmp(Long eid, Long[] roleIds) {
        String token = HttpRequestUtil.getToken();
        Employee emp = JSONObject.parseObject(redisTemplateService.getCacheObject(token), Employee.class);
        if (emp.getId().equals(eid)) {
            throw new BusinessException("无法为自己赋予职务");
        }
        //查询用户的信息，判断是否是系统管理员
        Employee employee = employeeService.getById(eid);
        if (employee.getIsAdmin()) {
            throw new BusinessException("无法操作系统管理员的职务");
        }
        /*根据员工编号清除关系*/
        roleMapper.clearRelationByEid(eid);
        /*重新保存关系*/
        List<Map<String, Object>> list = new ArrayList<>();
        for (Long roleId : roleIds) {
            Map<String, Object> map = new HashMap<>();
            map.put("eid", eid);
            map.put("rid", roleId);
            list.add(map);
        }
        if (list.size() > 0) {
            roleMapper.reSaveRelation(list);
        }
    }

    @Override
    public void clearEmpPermission(Long id) {
        roleMapper.clearRelationByEid(id);
    }
}
