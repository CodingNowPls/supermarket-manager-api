package com.rabbiter.market.inventory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.rabbiter.market.common.exception.BusinessException;
import com.rabbiter.market.common.redis.service.RedisTemplateService;
import com.rabbiter.market.goods.doamin.Goods;
import com.rabbiter.market.inventory.domain.GoodsStockDetail;
import com.rabbiter.market.inventory.domain.GoodsStock;
import com.rabbiter.market.inventory.domain.Warehouse;
import com.rabbiter.market.inventory.domain.Supplier;
import com.rabbiter.market.person.domain.Employee;
import com.rabbiter.market.inventory.mapper.StockGoodsDetailMapper;
import com.rabbiter.market.inventory.qo.QueryDetailStoreGoods;
import com.rabbiter.market.inventory.qo.QueryDetailStoreGoodsOut;
import com.rabbiter.market.goods.service.IGoodsService;
import com.rabbiter.market.inventory.service.IStockGoodsDetailService;
import com.rabbiter.market.inventory.service.IGoodsStockService;
import com.rabbiter.market.inventory.service.IWarehouseService;
import com.rabbiter.market.inventory.service.ISupplierService;
import com.rabbiter.market.goods.vo.StockGoodsOutDetailVo;
import com.rabbiter.market.goods.vo.StockGoodsDetailVo;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rabbiter.market.util.HttpRequestUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class StockGoodsDetailServiceImpl extends ServiceImpl<StockGoodsDetailMapper, GoodsStockDetail> implements IStockGoodsDetailService {

    @Autowired
    private RedisTemplateService redisTemplateService;
    @Autowired
    private IGoodsStockService goodsStoreService;
    @Autowired
    private IWarehouseService storeService;
    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private ISupplierService supplierService;

    @Override
    public void saveIn(GoodsStockDetail goodsStockDetail) {
        String token = HttpRequestUtil.getToken();
        Employee employee = JSONObject.parseObject(redisTemplateService.getCacheObject(token), Employee.class);
        goodsStockDetail.setType(GoodsStockDetail.TYPE_IN);
        goodsStockDetail.setState(GoodsStockDetail.STATE_NORMAL);
        goodsStockDetail.setCreateid(employee.getId());
        goodsStockDetail.setCreateby(employee.getNickName());
        goodsStockDetail.setCn(IdWorker.getIdStr());
        goodsStockDetail.setCreateTime(new Date());
        goodsStockDetail.setState1(GoodsStockDetail.STATE1_NORMAL);
        goodsStoreService.goodsInStore(goodsStockDetail.getGoodsId(), goodsStockDetail.getGoodsNum(), goodsStockDetail.getStoreId());
        Supplier supplier = supplierService.getById(goodsStockDetail.getSupplierId());
        goodsStockDetail.setSupplierName(supplier.getName());
        super.save(goodsStockDetail);
    }

    @Override
    public Page<StockGoodsDetailVo> queryPageByQoIn(QueryDetailStoreGoods qo) {
        Page<GoodsStockDetail> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        Page<StockGoodsDetailVo> page1 = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        LambdaQueryWrapper<GoodsStockDetail> wrapper = Wrappers.lambdaQuery(GoodsStockDetail.class)
                .likeRight(StringUtils.hasText(qo.getCn()), GoodsStockDetail::getCn, qo.getCn())
                .like(StringUtils.hasText(qo.getGoodsName()), GoodsStockDetail::getGoodsName, qo.getGoodsName())
                .eq(StringUtils.hasText(qo.getState1()), GoodsStockDetail::getState1, qo.getState1())
                .ge(StringUtils.hasText(qo.getStartCreateTime()), GoodsStockDetail::getCreateTime, qo.getStartCreateTime())
                .le(StringUtils.hasText(qo.getEndCreateTime()), GoodsStockDetail::getCreateTime, qo.getEndCreateTime())
                .eq(GoodsStockDetail::getType, GoodsStockDetail.TYPE_IN);
        super.page(page, wrapper);
        List<GoodsStockDetail> records = page.getRecords();
        if (records == null || records.size() <= 0) {
            page1.setTotal(0L);
        }
        List<StockGoodsDetailVo> list = new ArrayList<>();
        for (GoodsStockDetail record : records) {
            StockGoodsDetailVo vo = new StockGoodsDetailVo();
            BeanUtils.copyProperties(record, vo);
            Warehouse warehouse = storeService.getById(record.getStoreId());
            if (warehouse != null) {
                vo.setStoreName(warehouse.getName());
            }
            list.add(vo);
        }
        page1.setRecords(list);
        page1.setTotal(page.getTotal());
        return page1;
    }

    @Override
    public void delIn(String cn) {
        LambdaUpdateWrapper<GoodsStockDetail> wrapper = Wrappers.lambdaUpdate(GoodsStockDetail.class)
                .set(GoodsStockDetail::getState1, GoodsStockDetail.STATE1_DEL)
                .eq(GoodsStockDetail::getCn, cn);
        super.update(wrapper);
    }

    @Override
    public Page<StockGoodsOutDetailVo> queryPageByQoOut(QueryDetailStoreGoodsOut qo) {
        Page<GoodsStockDetail> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        Page<StockGoodsOutDetailVo> page1 = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        LambdaQueryWrapper<GoodsStockDetail> wrapper = Wrappers.lambdaQuery(GoodsStockDetail.class)
                .likeRight(StringUtils.hasText(qo.getCn()), GoodsStockDetail::getCn, qo.getCn())
                .like(StringUtils.hasText(qo.getGoodsName()), GoodsStockDetail::getGoodsName, qo.getGoodsName())
                .ge(StringUtils.hasText(qo.getStartCreateTime()), GoodsStockDetail::getCreateTime, qo.getStartCreateTime())
                .le(StringUtils.hasText(qo.getEndCreateTime()), GoodsStockDetail::getCreateTime, qo.getEndCreateTime())
                .eq(GoodsStockDetail::getType, GoodsStockDetail.TYPE_OUT)
                .eq(StringUtils.hasText(qo.getState()), GoodsStockDetail::getState, qo.getState())
                .eq(StringUtils.hasText(qo.getState1()), GoodsStockDetail::getState1, qo.getState1());

        super.page(page, wrapper);
        List<GoodsStockDetail> records = page.getRecords();
        if (records == null || records.size() <= 0) {
            page1.setTotal(0L);
        }
        List<StockGoodsOutDetailVo> list = new ArrayList<>();
        for (GoodsStockDetail record : records) {
            StockGoodsOutDetailVo vo = new StockGoodsOutDetailVo();
            BeanUtils.copyProperties(record, vo);
            Warehouse warehouse = storeService.getById(record.getStoreId());
            if (warehouse != null) {
                vo.setStoreName(warehouse.getName());
            }
            list.add(vo);
        }
        page1.setRecords(list);
        page1.setTotal(page.getTotal());
        return page1;
    }

    @Override
    public Map<String, Object> initOutOptions() {
        Set<Long> goodsIds = new HashSet<>();
        Set<Long> storeIds = new HashSet<>();
        LambdaQueryWrapper<GoodsStock> wrapper = Wrappers.lambdaQuery(GoodsStock.class)
                .gt(GoodsStock::getResidueNum, 0L);

        List<GoodsStock> list = goodsStoreService.list(wrapper);
        if (list == null || list.size() == 0) {
            throw new BusinessException("库存中没有存放商品");
        }
        for (GoodsStock goodsStock : list) {
            goodsIds.add(goodsStock.getGoodsId());
            storeIds.add(goodsStock.getStoreId());
        }
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> goodsList = new ArrayList<>();
        List<Map<String, Object>> storeList = new ArrayList<>();
        List<Goods> goods = goodsService.listByIds(goodsIds);
        for (Goods good : goods) {
            Map<String, Object> goodsMap = new HashMap<>();
            goodsMap.put("id", good.getId());
            goodsMap.put("name", good.getName());
            goodsList.add(goodsMap);
        }
        List<Warehouse> warehouses = storeService.listByIds(storeIds);
        for (Warehouse warehouse : warehouses) {
            Map<String, Object> storeMap = new HashMap<>();
            storeMap.put("id", warehouse.getId());
            storeMap.put("name", warehouse.getName());
            storeList.add(storeMap);
        }
        map.put("goods", goodsList);
        map.put("stores", storeList);
        return map;
    }

    @Override
    public List<Map<String, Object>> changeOutGoods(Long gid) {
        LambdaQueryWrapper<GoodsStock> wrapper = Wrappers.lambdaQuery(GoodsStock.class)
                .eq(GoodsStock::getGoodsId, gid)
                .gt(GoodsStock::getResidueNum, 0L);

        List<GoodsStock> list = goodsStoreService.list(wrapper);
        Set<Long> storeIds = new HashSet<>();
        for (GoodsStock goodsStock : list) {
            storeIds.add(goodsStock.getStoreId());
        }
        List<Warehouse> warehouses = storeService.listByIds(storeIds);
        List<Map<String, Object>> storeList = new ArrayList<>();
        for (Warehouse warehouse : warehouses) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", warehouse.getId());
            map.put("name", warehouse.getName());
            storeList.add(map);
        }
        return storeList;
    }

    @Override
    public List<Map<String, Object>> changeOutStore(Long storeId) {
        LambdaQueryWrapper<GoodsStock> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(GoodsStock::getStoreId, storeId)
                .gt(GoodsStock::getResidueNum, 0L);
        List<GoodsStock> list = goodsStoreService.list(wrapper);
        Set<Long> goodsIds = new HashSet<>();
        for (GoodsStock goodsStock : list) {
            goodsIds.add(goodsStock.getGoodsId());
        }
        List<Goods> goodsList = goodsService.listByIds(goodsIds);
        List<Map<String, Object>> goodsVo = new ArrayList<>();
        for (Goods goods : goodsList) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", goods.getId());
            map.put("name", goods.getName());
            goodsVo.add(map);
        }
        return goodsVo;
    }

    @Override
    public StockGoodsOutDetailVo queryOutGoods(Long goodsId, Long storeId) {
        LambdaQueryWrapper<GoodsStock> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(GoodsStock::getGoodsId, goodsId)
                .eq(GoodsStock::getStoreId, storeId);

        GoodsStock goodsStock = goodsStoreService.getOne(wrapper);
        StockGoodsOutDetailVo vo = new StockGoodsOutDetailVo();
        BeanUtils.copyProperties(goodsStock, vo);
        vo.setGoodsNum(goodsStock.getResidueNum());
        Goods goods = goodsService.getById(goodsId);
        vo.setGoodsName(goods.getName());
        return vo;
    }

    @Override
    public void saveOut(GoodsStockDetail goodsStockDetail) {
        String token = HttpRequestUtil.getToken();
        Employee employee = JSONObject.parseObject(redisTemplateService.getCacheObject(token), Employee.class);
        goodsStockDetail.setType(GoodsStockDetail.TYPE_OUT);
        goodsStockDetail.setState1(GoodsStockDetail.STATE1_NORMAL);
        goodsStockDetail.setCreateid(employee.getId());
        goodsStockDetail.setCreateby(employee.getNickName());
        goodsStockDetail.setCn(IdWorker.getIdStr());
        goodsStockDetail.setCreateTime(new Date());

        LambdaQueryWrapper<GoodsStock> goodsStoreQueryWrapper = Wrappers.lambdaQuery();
        goodsStoreQueryWrapper.eq(GoodsStock::getGoodsId, goodsStockDetail.getGoodsId())
                .eq(GoodsStock::getStoreId, goodsStockDetail.getStoreId());

        GoodsStock goodsStock = goodsStoreService.getOne(goodsStoreQueryWrapper);
        long num = goodsStock.getResidueNum() - goodsStockDetail.getGoodsNum();

        LambdaUpdateWrapper<GoodsStock> goodsStoreUpdateWrapper = Wrappers.lambdaUpdate();
        goodsStoreUpdateWrapper.eq(GoodsStock::getGoodsId, goodsStockDetail.getGoodsId())
                .eq(GoodsStock::getStoreId, goodsStockDetail.getStoreId());

        if (GoodsStockDetail.STATE_EXPIRY.equals(goodsStockDetail.getState())) {
            // 过期处理
            goodsStockDetail.setState(GoodsStockDetail.STATE_EXPIRY);
            if (num >= 0) {
                goodsStoreUpdateWrapper.set(GoodsStock::getResidueNum, num);
            } else {
                goodsStoreUpdateWrapper.set(GoodsStock::getResidueNum, 0L);
                goodsStockDetail.setGoodsNum(goodsStock.getResidueNum());
            }
        } else {
            // 出库到货架上
            goodsStockDetail.setState(GoodsStockDetail.STATE_NORMAL);
            Goods goods = goodsService.getById(goodsStockDetail.getGoodsId());

            LambdaUpdateWrapper<Goods> goodsUpdateWrapper = Wrappers.lambdaUpdate();
            goodsUpdateWrapper.eq(Goods::getId, goods.getId());

            if (num >= 0) {
                goodsStoreUpdateWrapper.set(GoodsStock::getResidueNum, num);
                goodsUpdateWrapper.set(Goods::getResidueNum, goods.getResidueNum() == null ? goodsStockDetail.getGoodsNum() : goods.getResidueNum() + goodsStockDetail.getGoodsNum());
            } else {
                goodsStoreUpdateWrapper.set(GoodsStock::getResidueNum, 0L);
                goodsUpdateWrapper.set(Goods::getResidueNum, goods.getResidueNum() == null ? goodsStock.getResidueNum() : goods.getResidueNum() + goodsStock.getResidueNum());
                goodsStockDetail.setGoodsNum(goodsStock.getResidueNum());
            }
            goodsService.update(goodsUpdateWrapper);
        }

        goodsStoreService.update(goodsStoreUpdateWrapper);

        super.save(goodsStockDetail);
    }
}
