package com.rabbiter.market.controller.inventory_management.supplier;

import com.rabbiter.market.common.sercurity.annotation.HasPermisson;
import com.rabbiter.market.common.web.response.JsonResult;
import com.rabbiter.market.domain.inventory_management.supplier.Supplier;
import com.rabbiter.market.qo.inventory_management.supplier.QuerySupplier;
import com.rabbiter.market.service.inventory_management.supplier.ISupplierService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/inventory_management/supplier")
public class SupplierController {
    @Autowired
    private ISupplierService supplierService;
    @HasPermisson("inventory_management:supplier:list")
    @PostMapping("/queryPageByQo")
    public JsonResult queryPageByQo(QuerySupplier qo) {
        Page<Supplier> page = supplierService.queryPageByQo(qo);
        return JsonResult.success(page);
    }
    @HasPermisson("inventory_management:supplier:save")
    @PostMapping("/save")
    public JsonResult saveSupplier(Supplier supplier){
        supplierService.saveSupplier(supplier);
        return JsonResult.success();
    }
    /*修改接口*/
    @HasPermisson("inventory_management:supplier:update")
    @PostMapping("/update")
    public JsonResult updateSupplier(Supplier supplier){
        supplierService.updateSupplier(supplier);
        return JsonResult.success();
    }
    @HasPermisson("inventory_management:supplier:update")
    @GetMapping("/queryByCn")
    public JsonResult queryByCn(Long cn){
        return JsonResult.success(supplierService.getById(cn));
    }
    @HasPermisson("inventory_management:supplier:deactivate")
    @PostMapping("/deactivate")
    public JsonResult deactivate(Long cn){
        supplierService.deactivate(cn);
        return JsonResult.success();
    }

}
