package com.rabbiter.market.inventory.service;

import com.rabbiter.market.inventory.domain.Store;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IStoreService extends IService<Store> {

    /**
     * 更改仓库信息的业务
     *
     * @param store
     */
    void updateStore(Store store);

    /**
     * 停用仓库业务
     *
     * @param sid
     */
    void deactivate(Long sid);

    Long getResidueNumByGoodsId(Long id);

    void saveStore(Store store);

}
