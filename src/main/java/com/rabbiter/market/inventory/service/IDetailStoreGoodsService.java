package com.rabbiter.market.inventory.service;

import com.rabbiter.market.inventory.domain.DetailStoreGoods;
import com.rabbiter.market.inventory.qo.QueryDetailStoreGoods;
import com.rabbiter.market.inventory.qo.QueryDetailStoreGoodsOut;
import com.rabbiter.market.goods.vo.DetailStoreGoodsOutVo;
import com.rabbiter.market.goods.vo.DetailStoreGoodsVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

public interface IDetailStoreGoodsService extends IService<DetailStoreGoods> {


    void saveIn(DetailStoreGoods detailStoreGoods, String token);

    Page<DetailStoreGoodsVo> queryPageByQoIn(QueryDetailStoreGoods qo);

    void delIn(String cn);

    Page<DetailStoreGoodsOutVo> queryPageByQoOut(QueryDetailStoreGoodsOut qo);

    Map<String, Object> initOutOptions();

    List<Map<String, Object>> changeOutGoods(Long gid);

    List<Map<String, Object>> changeOutStore(Long storeId);

    DetailStoreGoodsOutVo queryOutGoods(Long goodsId, Long storeId);

    void saveOut(DetailStoreGoods detailStoreGoods, String token);
}
