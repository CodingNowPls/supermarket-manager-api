package com.rabbiter.market.inventory.controller;

import com.rabbiter.market.common.sercurity.annotation.HasPermission;
import com.rabbiter.market.common.web.response.JsonResult;
import com.rabbiter.market.inventory.domain.DetailStoreGoods;
import com.rabbiter.market.inventory.qo.QueryDetailStoreGoodsOut;
import com.rabbiter.market.inventory.service.IDetailStoreGoodsService;
import com.rabbiter.market.goods.vo.DetailStoreGoodsOutVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotEmpty;
import java.util.Map;

@RestController
@Validated
@RequestMapping("/inventory_management/detail_store_goods_out")
public class StoreOutController {
    @Autowired
    private IDetailStoreGoodsService detailStoreGoodsService;

    @HasPermission("inventory_management:detail_store_goods_out:list")
    @PostMapping("/queryPageByQoOut")
    public JsonResult queryPageByQoOut(QueryDetailStoreGoodsOut qo) {
        Page<DetailStoreGoodsOutVo> page = detailStoreGoodsService.queryPageByQoOut(qo);
        return JsonResult.success(page);
    }

    @HasPermission("inventory_management:detail_store_goods_out:save")
    @GetMapping("/initOutOptions")
    public JsonResult<Map<String, Object>> initOutOptions() {
        return JsonResult.success(detailStoreGoodsService.initOutOptions());
    }

    @HasPermission("inventory_management:detail_store_goods_out:save")
    @GetMapping("/changeOutGoods")
    public JsonResult changeOutGoods(Long gid) {
        return JsonResult.success(detailStoreGoodsService.changeOutGoods(gid));
    }

    @HasPermission("inventory_management:detail_store_goods_out:save")
    @GetMapping("/changeOutStore")
    public JsonResult changeOutStore(Long storeId) {
        return JsonResult.success(detailStoreGoodsService.changeOutStore(storeId));
    }

    @HasPermission("inventory_management:detail_store_goods_out:save")
    @PostMapping("/queryOutGoods")
    public JsonResult queryOutGoods(@RequestParam("goodsId") Long goodsId,
                                    @RequestParam("storeId") Long storeId) {
        DetailStoreGoodsOutVo vo = detailStoreGoodsService.queryOutGoods(goodsId, storeId);
        return JsonResult.success(vo);
    }

    @HasPermission("inventory_management:detail_store_goods_out:save")
    @PostMapping("/save")
    public JsonResult saveOut(DetailStoreGoods detailStoreGoods, HttpServletRequest request) {
        detailStoreGoodsService.saveOut(detailStoreGoods, (String) request.getHeader("token"));
        return JsonResult.success();
    }

    @HasPermission("inventory_management:detail_store_goods_out:delOut")
    @PostMapping("/delOut")
    public JsonResult delOut(@NotEmpty(message = "系统编号不能为空") String cn) {
        detailStoreGoodsService.delIn(cn);
        return JsonResult.success();
    }
}
