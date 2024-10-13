package com.rabbiter.market.sale.service;

import com.rabbiter.market.goods.doamin.PointGoods;
import com.rabbiter.market.sale.domain.PointRedemption;
import com.rabbiter.market.inventory.qo.QueryExchangePointProductsRecords;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

public interface IPointRedemptionService extends IService<PointRedemption> {
    List<Map<String, Object>> queryPointProductBymemberId(Long memberId);

    PointGoods queryPointProductByGoodsId(Long goodsId);

    void saveExchangePointProductRecords(PointRedemption pointRedemption, String token);

    List<Map<String, Object>> queryOptionsMemberPhone();

    void delExchangePointProducts(String cn);

    Page<PointRedemption> queryPageByQoExchangePointProducts(QueryExchangePointProductsRecords qo);

    List<Map<String, Object>> queryOptionsPointProducts();

    List<Map<String, Object>> queryOptionsMember();

    List<Map<String, Object>> queryMemberByGoodsId(Long goodsId);
}
