package com.rabbiter.market.inventory.mapper;

import com.rabbiter.market.inventory.domain.GoodsStore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GoodsStoreMapper extends BaseMapper<GoodsStore> {
    Long storeUsed(Long id);

    Long getResidueNumByGoodsId(Long goodsId);

    void goodsInStore(@Param("goodsId") Long goodsId, @Param("goodsNum") Long goodsNum, @Param("storeId") Long storeId);

    void goodsOutStore(@Param("goodsId") Long goodsId, @Param("goodsNum") Long goodsNum, @Param("storeId") Long storeId);

    Long totalStoreNum();

    Long getTotalStoreNum1(Long storeId);
}
