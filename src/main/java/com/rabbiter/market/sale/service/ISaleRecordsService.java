package com.rabbiter.market.sale.service;

import com.rabbiter.market.sale.domain.SaleRecord;
import com.rabbiter.market.sale.qo.QuerySaleRecords;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

public interface ISaleRecordsService extends IService<SaleRecord> {
    List<Map<String, Object>> getOptionSaleRecordsGoods();

    SaleRecord saveSaleRecords(SaleRecord saleRecord, String token);

    Page<SaleRecord> queryPageByQoSaleRecords(QuerySaleRecords qo);

    void delSaleRecords(String cn);
}
