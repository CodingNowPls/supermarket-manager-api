package com.rabbiter.market.sale.service;

import com.rabbiter.market.goods.doamin.PointProducts;
import com.rabbiter.market.sale.domain.ExchangePointProducts;
import com.rabbiter.market.inventory.qo.QueryExchangePointProductsRecords;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

public interface IExchangePointProductsService extends IService<ExchangePointProducts> {
    List<Map<String, Object>> queryPointProductBymemberId(Long memberId);

    PointProducts queryPointProductByGoodsId(Long goodsId);

    void saveExchangePointProductRecords(ExchangePointProducts exchangePointProducts, String token);

    List<Map<String, Object>> queryOptionsMemberPhone();

    void delExchangePointProducts(String cn);

    Page<ExchangePointProducts> queryPageByQoExchangePointProducts(QueryExchangePointProductsRecords qo);

    List<Map<String, Object>> queryOptionsPointProducts();

    List<Map<String, Object>> queryOptionsMember();

    List<Map<String, Object>> queryMemberByGoodsId(Long goodsId);
}
