package com.rabbiter.market.goods.service;

import com.rabbiter.market.goods.doamin.PointGoods;
import com.rabbiter.market.goods.qo.QueryPointProducts;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

public interface IPointGoodsService extends IService<PointGoods> {
    Page<PointGoods> queryPageByQo(QueryPointProducts qo);

    List<Map<String, Object>> queryOptionGoods();

    void savePointGoods(PointGoods pointGoods, String token);

    void updatePointGoods(PointGoods pointGoods, String token);

    void del(Long id);
}
