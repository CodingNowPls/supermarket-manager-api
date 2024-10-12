package com.rabbiter.market.sale.service.impl;

import com.rabbiter.market.sale.domain.DetailSaleRecords;
import com.rabbiter.market.sale.mapper.DetailSaleRecordsMapper;
import com.rabbiter.market.sale.service.IDetailSaleRecordsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class DetailSaleRecordsServiceImpl extends ServiceImpl<DetailSaleRecordsMapper, DetailSaleRecords> implements IDetailSaleRecordsService {
}
