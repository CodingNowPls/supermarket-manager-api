package com.rabbiter.market.service.inventory_management.supplier;

import com.rabbiter.market.domain.inventory_management.supplier.Supplier;
import com.rabbiter.market.qo.inventory_management.supplier.QuerySupplier;
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
