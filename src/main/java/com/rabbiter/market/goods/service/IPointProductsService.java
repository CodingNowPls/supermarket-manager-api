package com.rabbiter.market.goods.service;

import com.rabbiter.market.goods.doamin.PointProducts;
import com.rabbiter.market.goods.qo.QueryPointProducts;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

public interface IPointProductsService extends IService<PointProducts> {
    Page<PointProducts> queryPageByQo(QueryPointProducts qo);

    List<Map<String, Object>> queryOptionGoods();

    void savePointGoods(PointProducts pointProducts, String token);

    void updatePointGoods(PointProducts pointProducts, String token);

    void del(Long id);
}
