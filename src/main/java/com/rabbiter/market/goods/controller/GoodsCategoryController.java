package com.rabbiter.market.goods.controller;

import com.rabbiter.market.common.sercurity.annotation.HasPermission;
import com.rabbiter.market.common.web.response.JsonResult;
import com.rabbiter.market.goods.doamin.GoodsCategory;
import com.rabbiter.market.goods.qo.QueryGoodsCategory;
import com.rabbiter.market.goods.service.IGoodsCategoryService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 商品分类
 * @author gao
 */
@RestController
@Validated
@RequestMapping("/goods/category")
public class GoodsCategoryController {
    @Autowired
    private IGoodsCategoryService goodsCategoryService;

    /*保存信息接口*/
    @HasPermission("goods:category:save")
    @PostMapping("/save")
    public JsonResult saveGoodsCategory(@RequestBody GoodsCategory category) {
        goodsCategoryService.saveGoodsCategory(category);
        return JsonResult.success();
    }

    /*修改接口*/
    @HasPermission("goods:category:update")
    @PostMapping("/update")
    public JsonResult updateGoodsCategory(@RequestBody GoodsCategory category) {
        goodsCategoryService.updateGoodsCategory(category);
        return JsonResult.success();
    }

    /*停用*/
    @HasPermission("goods:category:deactivate")
    @PostMapping("/deactivate")
    public JsonResult deactivate(Long cid) {
        goodsCategoryService.deactivate(cid);
        return JsonResult.success();
    }

    /*查询信息*/
    @HasPermission("goods:category:list")
    @PostMapping("/queryPageByQo")
    public JsonResult queryPageByQo(@RequestBody QueryGoodsCategory qo) {
        Page<GoodsCategory> page = goodsCategoryService.queryPageByQo(qo);
        return JsonResult.success(page);
    }

    @GetMapping("/normalCategoryAll")
    public JsonResult getNormalCategoryAll() {
        List<Map<String, Object>> list = goodsCategoryService.getNormalCategoryAll();
        return JsonResult.success(list);
    }
}
