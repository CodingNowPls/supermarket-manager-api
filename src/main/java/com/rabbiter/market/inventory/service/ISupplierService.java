package com.rabbiter.market.inventory.service;

import com.rabbiter.market.inventory.domain.Supplier;
import com.rabbiter.market.inventory.qo.QuerySupplier;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

public interface ISupplierService extends IService<Supplier> {


    void deactivate(Long cn);

    Page<Supplier> queryPageByQo(QuerySupplier qo);

    List<Map<String, Object>> queryOptionsSuppliers();

    void saveSupplier(Supplier supplier);

    void updateSupplier(Supplier supplier);
}
