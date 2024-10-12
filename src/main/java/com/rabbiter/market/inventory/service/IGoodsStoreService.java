package com.rabbiter.market.inventory.service;

import com.rabbiter.market.inventory.domain.GoodsStore;
import com.rabbiter.market.inventory.qo.QueryDetailStorageSituation;
import com.rabbiter.market.inventory.qo.QueryStorageSituation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

public interface IGoodsStoreService extends IService<GoodsStore> {

    Long storeUsed(Long id);

    Long getResidueNumByGoodsId(Long id);

    void goodsInStore(Long goodsId, Long goodsNum, Long storeId);

    void goodsOutStore(Long goodsId, Long goodsNum, Long storeId);

    Map<String, Object> queryPageStorageSituationByQo(QueryStorageSituation qo);

    Map<String, Object> queryStoreGoodsByStoreId(QueryDetailStorageSituation qo);
}
