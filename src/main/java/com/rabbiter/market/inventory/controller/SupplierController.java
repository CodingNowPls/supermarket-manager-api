package com.rabbiter.market.inventory.controller;

import com.rabbiter.market.common.sercurity.annotation.HasPermission;
import com.rabbiter.market.common.web.response.JsonResult;
import com.rabbiter.market.inventory.domain.Supplier;
import com.rabbiter.market.inventory.qo.QuerySupplier;
import com.rabbiter.market.inventory.service.ISupplierService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/inventory/supplier")
public class SupplierController {
    @Autowired
    private ISupplierService supplierService;

    @HasPermission("inventory:supplier:list")
    @PostMapping("/queryPageByQo")
    public JsonResult queryPageByQo(@RequestBody QuerySupplier qo) {
        Page<Supplier> page = supplierService.queryPageByQo(qo);
        return JsonResult.success(page);
    }

    @HasPermission("inventory:supplier:save")
    @PostMapping("/save")
    public JsonResult saveSupplier(@RequestBody Supplier supplier) {
        supplierService.saveSupplier(supplier);
        return JsonResult.success();
    }

    /*修改接口*/
    @HasPermission("inventory:supplier:update")
    @PostMapping("/update")
    public JsonResult updateSupplier(@RequestBody Supplier supplier) {
        supplierService.updateSupplier(supplier);
        return JsonResult.success();
    }

    @HasPermission("inventory:supplier:update")
    @GetMapping("/queryByCn")
    public JsonResult queryByCn(Long cn) {
        return JsonResult.success(supplierService.getById(cn));
    }

    @HasPermission("inventory:supplier:deactivate")
    @PostMapping("/deactivate")
    public JsonResult deactivate(Long cn) {
        supplierService.deactivate(cn);
        return JsonResult.success();
    }

}
