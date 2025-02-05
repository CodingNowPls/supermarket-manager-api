package com.rabbiter.market.sale.mapper;

import com.rabbiter.market.sale.domain.SaleRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SaleRecordsMapper extends BaseMapper<SaleRecord> {
}
