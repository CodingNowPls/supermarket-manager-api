package com.rabbiter.market.inventory.service.impl;

import com.rabbiter.market.common.exception.BusinessException;
import com.rabbiter.market.inventory.domain.Warehouse;
import com.rabbiter.market.inventory.mapper.WarehouseMapper;
import com.rabbiter.market.inventory.service.IGoodsStockService;
import com.rabbiter.market.inventory.service.IWarehouseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
@Service
public class WarehouseServiceImpl extends ServiceImpl<WarehouseMapper, Warehouse> implements IWarehouseService {
    @Autowired
    private IGoodsStockService goodsStoreService;

    @Override
    public void updateStore(Warehouse warehouse) {
        //判断是否要将状态修改为停用状态
        QueryWrapper<Warehouse> storeQueryWrapper = new QueryWrapper<Warehouse>()
                .eq("name", warehouse.getName())
                .eq("address", warehouse.getAddress())
                .eq("state", warehouse.getState());
        Warehouse one = super.getOne(storeQueryWrapper);
        if (Warehouse.STATE_BAN.equals(warehouse.getState())) {
            Long redisueNum = goodsStoreService.storeUsed(warehouse.getId());
            //要修改为停用状态
            if (redisueNum != null && redisueNum != 0) {
                throw new BusinessException("仓库中存在商品，不能停用仓库");
            }
            if (one != null) {
                super.removeById(one);
            }

        }

        super.updateById(warehouse);
    }

    @Transactional
    @Override
    public void deactivate(Long sid) {
        Long redisueNum = goodsStoreService.storeUsed(sid);
        if (redisueNum != null && redisueNum != 0) {
            throw new BusinessException("仓库中存在商品，不能停用仓库");
        } else {
            UpdateWrapper<Warehouse> wrapper = new UpdateWrapper<Warehouse>()
                    .set("state", Warehouse.STATE_BAN)
                    .eq("id", sid);
            super.update(wrapper);
        }
    }

    @Override
    public Long getResidueNumByGoodsId(Long id) {
        return goodsStoreService.getResidueNumByGoodsId(id);
    }

    @Override
    public void saveStore(Warehouse warehouse) {
        warehouse.setState(Warehouse.STATE_NORMAL);
        QueryWrapper<Warehouse> queryWrapper = new QueryWrapper<Warehouse>().eq("state", Warehouse.STATE_NORMAL)
                .eq("name", warehouse.getName())
                .eq("address", warehouse.getAddress());
        Warehouse one = super.getOne(queryWrapper);
        if (one != null) {
            throw new BusinessException("创建失败，已有相同的仓库");
        }
        super.save(warehouse);
    }
}
