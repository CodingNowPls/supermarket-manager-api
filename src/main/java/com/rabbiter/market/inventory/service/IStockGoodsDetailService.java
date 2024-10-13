package com.rabbiter.market.inventory.service;

import com.rabbiter.market.inventory.domain.GoodsStockDetail;
import com.rabbiter.market.inventory.qo.QueryDetailStoreGoods;
import com.rabbiter.market.inventory.qo.QueryDetailStoreGoodsOut;
import com.rabbiter.market.goods.vo.StockGoodsOutDetailVo;
import com.rabbiter.market.goods.vo.StockGoodsDetailVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

public interface IStockGoodsDetailService extends IService<GoodsStockDetail> {

    void saveIn(GoodsStockDetail goodsStockDetail);

    Page<StockGoodsDetailVo> queryPageByQoIn(QueryDetailStoreGoods qo);

    void delIn(String cn);

    Page<StockGoodsOutDetailVo> queryPageByQoOut(QueryDetailStoreGoodsOut qo);

    Map<String, Object> initOutOptions();

    List<Map<String, Object>> changeOutGoods(Long gid);

    List<Map<String, Object>> changeOutStore(Long storeId);

    StockGoodsOutDetailVo queryOutGoods(Long goodsId, Long storeId);

    void saveOut(GoodsStockDetail goodsStockDetail);
}
