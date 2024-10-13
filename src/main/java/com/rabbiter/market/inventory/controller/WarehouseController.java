package com.rabbiter.market.inventory.controller;

import com.rabbiter.market.common.sercurity.annotation.HasPermission;
import com.rabbiter.market.common.web.response.JsonResult;
import com.rabbiter.market.inventory.domain.Warehouse;
import com.rabbiter.market.inventory.qo.QueryStore;
import com.rabbiter.market.inventory.service.IWarehouseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 仓库管理
 */
@RestController
@Validated
@RequestMapping("/inventory/warehouse")
public class WarehouseController {
    @Autowired
    private IWarehouseService storeService;

    /*保存仓库信息接口*/
    @HasPermission("inventory:warehouse:save")
    @PostMapping("/save")
    public JsonResult saveStore(Warehouse warehouse) {
        storeService.saveStore(warehouse);
        return JsonResult.success();
    }

    /*修改仓库接口*/
    @HasPermission("inventory:warehouse:update")
    @PostMapping("/update")
    public JsonResult updateStore(Warehouse warehouse) {
        storeService.updateStore(warehouse);
        return JsonResult.success();
    }

    /*停用仓库*/
    @HasPermission("inventory:warehouse:deactivate")
    @PostMapping("/deactivate")
    public JsonResult deactivate(Long sid) {
        storeService.deactivate(sid);
        return JsonResult.success();
    }

    /*查询仓库信息*/
    @HasPermission("inventory:warehouse:list")
    @PostMapping("/list")
    public JsonResult list(QueryStore qo) {
        return JsonResult.success(storeService.list(new QueryWrapper<Warehouse>()
                .like(StringUtils.hasText(qo.getName()), "name", qo.getName())
                .eq(StringUtils.hasText(qo.getState()), "state", qo.getState())
        ));
    }

}
