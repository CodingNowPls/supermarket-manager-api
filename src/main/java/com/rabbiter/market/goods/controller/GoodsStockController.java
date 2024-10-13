package com.rabbiter.market.goods.controller;

import com.rabbiter.market.common.sercurity.annotation.HasPermission;
import com.rabbiter.market.common.web.response.JsonResult;
import com.rabbiter.market.goods.qo.QueryGoodsStore;
import com.rabbiter.market.goods.service.IGoodsService;
import com.rabbiter.market.goods.vo.GoodsStockVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 商品库存
 * @author gao
 */
@RestController
@Validated
@RequestMapping("/goods/stock")
public class GoodsStockController {
    @Autowired
    private IGoodsService goodsService;

    /*查询信息*/
    @HasPermission("goods:goods:list")
    @PostMapping("/queryPageByQo")
    public JsonResult queryPageByQo(@RequestBody QueryGoodsStore qo) {
        Page<GoodsStockVo> page = goodsService.queryPageGoodsStore(qo);
        return JsonResult.success(page);
    }

    @GetMapping("/queryGoodsStoreById")
    public JsonResult queryGoodsStoreById(Long id) {
        GoodsStockVo vo = goodsService.queryGoodsStoreById(id);
        return JsonResult.success(vo);
    }

    @PostMapping("/updateInventory")
    public JsonResult updateInventory(@RequestBody GoodsStockVo vo) {
        goodsService.updateInventory(vo);
        return JsonResult.success();
    }

}
