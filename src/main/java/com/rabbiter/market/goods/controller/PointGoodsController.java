package com.rabbiter.market.goods.controller;

import com.rabbiter.market.common.sercurity.annotation.HasPermission;
import com.rabbiter.market.common.web.response.JsonResult;
import com.rabbiter.market.goods.doamin.PointGoods;
import com.rabbiter.market.goods.qo.QueryPointProducts;
import com.rabbiter.market.goods.service.IPointGoodsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 积分商品
 * @author gao
 */
@RestController
@Validated
@RequestMapping("/goods/pointGoods")
public class PointGoodsController {
    @Autowired
    private IPointGoodsService pointProductsService;

    /*查询信息*/
    @HasPermission("goods:pointGoods:list")
    @PostMapping("/queryPageByQo")
    public JsonResult queryPageByQo(@RequestBody QueryPointProducts qo) {
        Page<PointGoods> page = pointProductsService.queryPageByQo(qo);
        return JsonResult.success(page);
    }

    @GetMapping("/del")
    public JsonResult del(Long id) {
        pointProductsService.del(id);
        return JsonResult.success();
    }

    @GetMapping("/queryOptionGoods")
    public JsonResult queryOptionGoods() {
        List<Map<String, Object>> list = pointProductsService.queryOptionGoods();
        return JsonResult.success(list);
    }

    @PostMapping("/savePointGoods")
    public JsonResult savePointGoods(@RequestBody PointGoods pointGoods) {
        pointProductsService.savePointGoods(pointGoods);
        return JsonResult.success();
    }

    @GetMapping("/queryPointGoodsById")
    public JsonResult queryPointGoodsById(@RequestParam Long goodsId) {
        PointGoods pointGoods = pointProductsService.getOne(new QueryWrapper<PointGoods>().eq("goods_id", goodsId));
        return JsonResult.success(pointGoods);
    }

    @PostMapping("/updatePointGoods")
    public JsonResult updatePointGoods(@RequestBody PointGoods pointGoods) {
        pointProductsService.updatePointGoods(pointGoods);
        return JsonResult.success();
    }

}
