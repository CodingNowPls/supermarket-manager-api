package com.rabbiter.market.system.controller;

import com.rabbiter.market.common.sercurity.annotation.HasPermission;
import com.rabbiter.market.common.web.response.JsonResult;
import com.rabbiter.market.system.domain.Menu;
import com.rabbiter.market.system.qo.MenuQuery;
import com.rabbiter.market.system.service.IMenuService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 菜单管理
 *
 * @author gao
 */
@RestController
@RequestMapping("/system/menu")
public class MenuController {
    @Autowired
    private IMenuService menuService;

    /**
     * 条件分页查询菜单的信息
     *
     * @param qo
     * @return
     */
    @HasPermission("system:menu:list")
    @PostMapping("/queryPageByQo")
    public JsonResult queryPageByQo(@RequestBody MenuQuery qo) {
        Page<Menu> page = menuService.queryPageByQo(qo);
        return JsonResult.success(page);
    }
}
