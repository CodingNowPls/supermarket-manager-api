package com.rabbiter.market.goods.controller;

import com.rabbiter.market.common.sercurity.annotation.HasPermission;
import com.rabbiter.market.common.web.response.JsonResult;
import com.rabbiter.market.goods.qo.QueryGoodsStore;
import com.rabbiter.market.goods.service.IGoodsService;
import com.rabbiter.market.goods.vo.GoodsStoreVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/goods_management/goods_store")
public class GoodsStoreController {
    @Autowired
    private IGoodsService goodsService;

    /*查询信息*/
    @HasPermission("goods_management:goods_store:list")
    @PostMapping("/queryPageByQo")
    public JsonResult queryPageByQo(QueryGoodsStore qo) {
        Page<GoodsStoreVo> page = goodsService.queryPageGoodsStore(qo);
        return JsonResult.success(page);
    }

    @GetMapping("/queryGoodsStoreById")
    public JsonResult queryGoodsStoreById(Long id) {
        GoodsStoreVo vo = goodsService.queryGoodsStoreById(id);
        return JsonResult.success(vo);
    }

    @PostMapping("/updateInventory")
    public JsonResult updateInventory(GoodsStoreVo vo) {
        goodsService.updateInventory(vo);
        return JsonResult.success();
    }

}
