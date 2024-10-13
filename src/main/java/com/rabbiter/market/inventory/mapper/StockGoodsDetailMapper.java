package com.rabbiter.market.inventory.mapper;

import com.rabbiter.market.inventory.domain.GoodsStockDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StockGoodsDetailMapper extends BaseMapper<GoodsStockDetail> {
}
