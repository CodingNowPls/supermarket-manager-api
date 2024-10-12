package com.rabbiter.market.inventory.controller;

import com.rabbiter.market.common.sercurity.annotation.HasPermission;
import com.rabbiter.market.common.web.response.JsonResult;
import com.rabbiter.market.inventory.qo.QueryDetailStorageSituation;
import com.rabbiter.market.inventory.qo.QueryStorageSituation;
import com.rabbiter.market.inventory.service.IGoodsStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Validated
@RequestMapping("/inventory_management/store/storage_situation")
public class StorageSituationController {
    @Autowired
    private IGoodsStoreService goodsStoreService;

    @HasPermission("inventory_management:store:storage_situation")
    @PostMapping("/queryPageByQo")
    public JsonResult queryPageByQo(QueryStorageSituation qo) {
        Map<String, Object> map = goodsStoreService.queryPageStorageSituationByQo(qo);
        return JsonResult.success(map);

    }

    @HasPermission("inventory_management:store:storage_situation")
    @PostMapping("/queryStoreGoodsByStoreId")
    public JsonResult queryStoreGoodsByStoreId(QueryDetailStorageSituation qo) {
        Map<String, Object> map = goodsStoreService.queryStoreGoodsByStoreId(qo);
        return JsonResult.success(map);

    }

}
