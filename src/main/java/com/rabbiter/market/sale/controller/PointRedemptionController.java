package com.rabbiter.market.sale.controller;

import com.rabbiter.market.common.sercurity.annotation.HasPermission;
import com.rabbiter.market.common.web.response.JsonResult;
import com.rabbiter.market.goods.doamin.PointGoods;
import com.rabbiter.market.sale.domain.PointRedemption;
import com.rabbiter.market.inventory.qo.QueryExchangePointProductsRecords;
import com.rabbiter.market.sale.service.IPointRedemptionService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 积分兑换记录
 */
@RestController
@Validated
@RequestMapping("/sale/pointRedemption")
public class PointRedemptionController {
    @Autowired
    private IPointRedemptionService exchangePointProductsService;


    @GetMapping("/queryPointProductBymemberId")
    public JsonResult queryPointProductBymemberId(Long memberId) {
        List<Map<String, Object>> list = exchangePointProductsService.queryPointProductBymemberId(memberId);
        return JsonResult.success(list);
    }


    @GetMapping("/queryMemberByGoodsId")
    public JsonResult queryMemberByGoodsId(Long goodsId) {
        List<Map<String, Object>> list = exchangePointProductsService.queryMemberByGoodsId(goodsId);
        return JsonResult.success(list);
    }

    @GetMapping("/queryPointProductByGoodsId")
    public JsonResult queryPointProductByGoodsId(Long goodsId) {
        PointGoods pointGoods = exchangePointProductsService.queryPointProductByGoodsId(goodsId);
        return JsonResult.success(pointGoods);
    }

    /**
     * 兑换积分商品
     *
     * @param pointRedemption
     * @return
     */
    @PostMapping("/savePointRedemptionRecords")
    public JsonResult saveExchangePointProductRecords(@RequestBody PointRedemption pointRedemption) {
        exchangePointProductsService.saveExchangePointProductRecords(pointRedemption);
        return JsonResult.success();
    }

    @GetMapping("/queryOptionsMemberPhone")
    public JsonResult queryOptionsMemberPhone() {
        List<Map<String, Object>> list = exchangePointProductsService.queryOptionsMemberPhone();
        return JsonResult.success(list);
    }

    @GetMapping("/delExchangePointProducts")
    public JsonResult delExchangePointProducts(String cn) {
        exchangePointProductsService.delExchangePointProducts(cn);
        return JsonResult.success();
    }

    @HasPermission("sale:pointRedemptionHistory")
    @PostMapping("/queryPageByQoExchangePointProducts")
    public JsonResult queryPageByQoExchangePointProducts(@RequestBody QueryExchangePointProductsRecords qo) {
        Page<PointRedemption> page = exchangePointProductsService.queryPageByQoExchangePointProducts(qo);
        return JsonResult.success(page);
    }

    @GetMapping("/queryOptionsPointProducts")
    public JsonResult queryOptionsPointProducts() {
        List<Map<String, Object>> list = exchangePointProductsService.queryOptionsPointProducts();
        return JsonResult.success(list);
    }

    @GetMapping("/queryOptionsMember")
    public JsonResult queryOptionsMember() {
        List<Map<String, Object>> list = exchangePointProductsService.queryOptionsMember();
        return JsonResult.success(list);
    }


}
