package com.rabbiter.market.inventory.controller;

import com.rabbiter.market.common.sercurity.annotation.HasPermission;
import com.rabbiter.market.common.web.response.JsonResult;
import com.rabbiter.market.inventory.domain.GoodsStockDetail;
import com.rabbiter.market.inventory.qo.QueryDetailStoreGoods;
import com.rabbiter.market.inventory.service.IStockGoodsDetailService;
import com.rabbiter.market.inventory.service.ISupplierService;
import com.rabbiter.market.goods.vo.StockGoodsDetailVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Map;

@RestController
@Validated
@RequestMapping("/inventory/stockIn")
public class StockInController {
    @Autowired
    private IStockGoodsDetailService detailStoreGoodsService;
    @Autowired
    private ISupplierService supplierService;

    @HasPermission("inventory:detail_store_goods_in:save")
    @PostMapping("/save")
    public JsonResult saveIn(@RequestBody GoodsStockDetail goodsStockDetail) {
        detailStoreGoodsService.saveIn(goodsStockDetail);
        return JsonResult.success();
    }

    @HasPermission("inventory:detail_store_goods_in:list")
    @PostMapping("/queryPageByQo")
    public JsonResult queryPageByQo(@RequestBody QueryDetailStoreGoods qo) {
        Page<StockGoodsDetailVo> page = detailStoreGoodsService.queryPageByQoIn(qo);
        return JsonResult.success(page);
    }

    @HasPermission("inventory:detail_store_goods_in:delIn")
    @PostMapping("/delIn")
    public JsonResult delIn(@NotEmpty(message = "系统编号不能为空") String cn) {
        detailStoreGoodsService.delIn(cn);
        return JsonResult.success();
    }

    @HasPermission("inventory:detail_store_goods_in:save")
    @GetMapping("/queryOptionsSuppliers")
    public JsonResult queryOptionsSuppliers() {
        List<Map<String, Object>> list = supplierService.queryOptionsSuppliers();
        return JsonResult.success(list);
    }
}
