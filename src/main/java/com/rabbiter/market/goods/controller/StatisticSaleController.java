package com.rabbiter.market.goods.controller;

import com.rabbiter.market.common.sercurity.annotation.HasPermission;
import com.rabbiter.market.common.web.response.JsonResult;
import com.rabbiter.market.goods.qo.QueryStatisticSale;
import com.rabbiter.market.goods.service.IGoodsService;
import com.rabbiter.market.sale.vo.SalesStatisticsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/goods_management/statistic_sale")
public class StatisticSaleController {
    @Autowired
    private IGoodsService goodsService;

    @HasPermission("goods_management:statistic_sale:list")
    @PostMapping("/queryPageByQo")
    public JsonResult queryPageByQo(QueryStatisticSale qo) {
        SalesStatisticsVo vo = goodsService.queryPageStatisticSaleByQo(qo);
        return JsonResult.success(vo);
    }

}
