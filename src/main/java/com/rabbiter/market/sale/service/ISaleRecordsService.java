package com.rabbiter.market.sale.service;

import com.rabbiter.market.sale.domain.SaleRecords;
import com.rabbiter.market.sale.qo.QuerySaleRecords;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

public interface ISaleRecordsService extends IService<SaleRecords> {
    List<Map<String, Object>> getOptionSaleRecordsGoods();

    SaleRecords saveSaleRecords(SaleRecords saleRecords, String token);

    Page<SaleRecords> queryPageByQoSaleRecords(QuerySaleRecords qo);

    void delSaleRecords(String cn);
}
