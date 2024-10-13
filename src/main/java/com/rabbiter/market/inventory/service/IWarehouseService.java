package com.rabbiter.market.inventory.service;

import com.rabbiter.market.inventory.domain.Warehouse;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IWarehouseService extends IService<Warehouse> {

    /**
     * 更改仓库信息的业务
     *
     * @param warehouse
     */
    void updateStore(Warehouse warehouse);

    /**
     * 停用仓库业务
     *
     * @param sid
     */
    void deactivate(Long sid);

    Long getResidueNumByGoodsId(Long id);

    void saveStore(Warehouse warehouse);

}
