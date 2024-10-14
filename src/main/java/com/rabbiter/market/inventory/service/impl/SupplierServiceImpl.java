package com.rabbiter.market.inventory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.rabbiter.market.common.exception.BusinessException;
import com.rabbiter.market.inventory.domain.GoodsStockDetail;
import com.rabbiter.market.inventory.domain.Supplier;
import com.rabbiter.market.inventory.mapper.SupplierMapper;
import com.rabbiter.market.inventory.qo.QuerySupplier;
import com.rabbiter.market.inventory.service.IStockGoodsDetailService;
import com.rabbiter.market.inventory.service.ISupplierService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Service
public class SupplierServiceImpl extends ServiceImpl<SupplierMapper, Supplier> implements ISupplierService {
    @Autowired
    private IStockGoodsDetailService detailStoreGoodsService;

    @Override
    public void deactivate(Long cn) {
        LambdaQueryWrapper<GoodsStockDetail> detailStoreGoodsQueryWrapper = Wrappers.lambdaQuery();
        detailStoreGoodsQueryWrapper.eq(GoodsStockDetail::getState1, GoodsStockDetail.STATE1_NORMAL)
                .eq(GoodsStockDetail::getType, GoodsStockDetail.TYPE_IN)
                .eq(GoodsStockDetail::getState, GoodsStockDetail.STATE_NORMAL)
                .eq(GoodsStockDetail::getSupplierId, cn);

        List<GoodsStockDetail> list = detailStoreGoodsService.list(detailStoreGoodsQueryWrapper);
        if (list != null && list.size() > 0) {
            throw new BusinessException("该供货商正在被入库订单使用，请解除关系之后在停用");
        }
        LambdaUpdateWrapper<Supplier> updateWrapper = Wrappers.lambdaUpdate();
        updateWrapper.set(Supplier::getState, Supplier.STATE_BAN)
                .eq(Supplier::getCn, cn);
        super.update(updateWrapper);
    }

    @Override
    public Page<Supplier> queryPageByQo(QuerySupplier qo) {
        Page<Supplier> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        LambdaQueryWrapper<Supplier> wrapper = Wrappers.lambdaQuery();
        if (StringUtils.hasText(qo.getName())) {
            wrapper.like(Supplier::getName, qo.getName());
        }
        if (StringUtils.hasText(qo.getAddress())) {
            wrapper.like(Supplier::getAddress, qo.getAddress());
        }
        if (StringUtils.hasText(qo.getInfo())) {
            wrapper.like(Supplier::getInfo, qo.getInfo());
        }
        wrapper.eq(Supplier::getState, Supplier.STATE_NORMAL);
        super.page(page, wrapper);
        return page;
    }

    @Override
    public List<Map<String, Object>> queryOptionsSuppliers() {
        List<Map<String, Object>> list = new ArrayList<>();
        LambdaQueryWrapper<Supplier> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Supplier::getState, Supplier.STATE_NORMAL);
        List<Supplier> suppliers = super.list(wrapper);
        if (suppliers == null || suppliers.size() <= 0) {
            return new ArrayList<>();
        }
        for (Supplier supplier : suppliers) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", supplier.getCn());
            map.put("name", supplier.getName());
            list.add(map);
        }
        return list;

    }

    @Override
    public void saveSupplier(Supplier supplier) {
        supplier.setState(Supplier.STATE_NORMAL);
        LambdaQueryWrapper<Supplier> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Supplier::getName, supplier.getName())
                .eq(Supplier::getState, Supplier.STATE_NORMAL);

        Supplier one = super.getOne(queryWrapper);
        if (one != null) {
            throw new BusinessException("已存在供货商的联系方式");
        }
        super.save(supplier);
    }

    @Override
    public void updateSupplier(Supplier supplier) {
        if (Supplier.STATE_NORMAL.equals(supplier.getState())) {
            LambdaQueryWrapper<Supplier> queryWrapper = Wrappers.lambdaQuery();
            queryWrapper.eq(Supplier::getName, supplier.getName())
                    .eq(Supplier::getState, Supplier.STATE_NORMAL)
                    .ne(Supplier::getCn, supplier.getCn());

            Supplier one = super.getOne(queryWrapper);
            if (one != null) {
                throw new BusinessException("该供货商已存在");
            }
        }
        super.updateById(supplier);
    }
}
