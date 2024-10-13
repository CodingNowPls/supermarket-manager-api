package com.rabbiter.market.inventory.controller;

import com.rabbiter.market.common.sercurity.annotation.HasPermission;
import com.rabbiter.market.common.web.response.JsonResult;
import com.rabbiter.market.inventory.qo.QueryDetailStorageSituation;
import com.rabbiter.market.inventory.qo.QueryStorageSituation;
import com.rabbiter.market.inventory.service.IGoodsStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 *
 */
@RestController
@Validated
@RequestMapping("/inventory/stock/statistic")
public class StockStatisticController {
    @Autowired
    private IGoodsStockService goodsStoreService;

    @HasPermission("inventory:stock:statistic:list")
    @PostMapping("/queryPageByQo")
    public JsonResult queryPageByQo(@RequestBody QueryStorageSituation qo) {
        Map<String, Object> map = goodsStoreService.queryPageStorageSituationByQo(qo);
        return JsonResult.success(map);

    }

    @HasPermission("inventory:stock:statistic")
    @PostMapping("/queryStoreGoodsByStoreId")
    public JsonResult queryStoreGoodsByStoreId(@RequestBody QueryDetailStorageSituation qo) {
        Map<String, Object> map = goodsStoreService.queryStoreGoodsByStoreId(qo);
        return JsonResult.success(map);

    }

}
