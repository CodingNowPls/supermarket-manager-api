package com.rabbiter.market.inventory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
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
        LambdaQueryWrapper<Warehouse> storeQueryWrapper = Wrappers.lambdaQuery();
        storeQueryWrapper.eq(Warehouse::getName, warehouse.getName())
                .eq(Warehouse::getAddress, warehouse.getAddress())
                .eq(Warehouse::getState, warehouse.getState());

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
            LambdaUpdateWrapper<Warehouse> wrapper = Wrappers.lambdaUpdate();
            wrapper.set(Warehouse::getState, Warehouse.STATE_BAN)
                    .eq(Warehouse::getId, sid);

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
        LambdaQueryWrapper<Warehouse> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Warehouse::getState, Warehouse.STATE_NORMAL)
                .eq(Warehouse::getName, warehouse.getName())
                .eq(Warehouse::getAddress, warehouse.getAddress());
        Warehouse one = super.getOne(queryWrapper);
        if (one != null) {
            throw new BusinessException("创建失败，已有相同的仓库");
        }
        super.save(warehouse);
    }
}
