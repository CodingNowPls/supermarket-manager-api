package com.rabbiter.market.sale.service;

import com.rabbiter.market.goods.doamin.PointProducts;
import com.rabbiter.market.sale.domain.PointRedemptionHistory;
import com.rabbiter.market.inventory.qo.QueryExchangePointProductsRecords;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

public interface IPointRedemptionHistoryService extends IService<PointRedemptionHistory> {
    List<Map<String, Object>> queryPointProductBymemberId(Long memberId);

    PointProducts queryPointProductByGoodsId(Long goodsId);

    void saveExchangePointProductRecords(PointRedemptionHistory pointRedemptionHistory, String token);

    List<Map<String, Object>> queryOptionsMemberPhone();

    void delExchangePointProducts(String cn);

    Page<PointRedemptionHistory> queryPageByQoExchangePointProducts(QueryExchangePointProductsRecords qo);

    List<Map<String, Object>> queryOptionsPointProducts();

    List<Map<String, Object>> queryOptionsMember();

    List<Map<String, Object>> queryMemberByGoodsId(Long goodsId);
}
