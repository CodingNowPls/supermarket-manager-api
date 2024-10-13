package com.rabbiter.market.inventory.service.impl;

import com.rabbiter.market.common.exception.BusinessException;
import com.rabbiter.market.common.redis.service.RedisTemplateService;
import com.rabbiter.market.goods.doamin.Goods;
import com.rabbiter.market.inventory.domain.StockDetailGoods;
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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class StockGoodsDetailServiceImpl extends ServiceImpl<StockGoodsDetailMapper, StockDetailGoods> implements IStockGoodsDetailService {

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
    public void saveIn(StockDetailGoods stockDetailGoods, String token) {
        Employee employee = JSONObject.parseObject(redisTemplateService.getCacheObject(token), Employee.class);
        stockDetailGoods.setType(StockDetailGoods.TYPE_IN);
        stockDetailGoods.setState(StockDetailGoods.STATE_NORMAL);
        stockDetailGoods.setCreateid(employee.getId());
        stockDetailGoods.setCreateby(employee.getNickName());
        stockDetailGoods.setCn(IdWorker.getIdStr());
        stockDetailGoods.setCreateTime(new Date());
        stockDetailGoods.setState1(StockDetailGoods.STATE1_NORMAL);
        goodsStoreService.goodsInStore(stockDetailGoods.getGoodsId(), stockDetailGoods.getGoodsNum(), stockDetailGoods.getStoreId());
        Supplier supplier = supplierService.getById(stockDetailGoods.getSupplierId());
        stockDetailGoods.setSupplierName(supplier.getName());
        super.save(stockDetailGoods);
    }

    @Override
    public Page<StockGoodsDetailVo> queryPageByQoIn(QueryDetailStoreGoods qo) {
        Page<StockDetailGoods> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        Page<StockGoodsDetailVo> page1 = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        QueryWrapper<StockDetailGoods> wrapper = new QueryWrapper<>();
        wrapper.likeRight(StringUtils.hasText(qo.getCn()), "cn", qo.getCn());
        wrapper.like(StringUtils.hasText(qo.getGoodsName()), "goods_name", qo.getGoodsName());
        wrapper.eq(StringUtils.hasText(qo.getState1()), "state1", qo.getState1());
        wrapper.ge(StringUtils.hasText(qo.getStartCreateTime()), "create_time", qo.getStartCreateTime());
        wrapper.le(StringUtils.hasText(qo.getEndCreateTime()), "create_time", qo.getEndCreateTime());
        wrapper.eq("type", StockDetailGoods.TYPE_IN);
        super.page(page, wrapper);
        List<StockDetailGoods> records = page.getRecords();
        if (records == null || records.size() <= 0) {
            page1.setTotal(0L);
        }
        List<StockGoodsDetailVo> list = new ArrayList<>();
        for (StockDetailGoods record : records) {
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
        UpdateWrapper<StockDetailGoods> wrapper = new UpdateWrapper<>();
        wrapper.set("state1", StockDetailGoods.STATE1_DEL);
        wrapper.eq("cn", cn);
        super.update(wrapper);
    }

    @Override
    public Page<StockGoodsOutDetailVo> queryPageByQoOut(QueryDetailStoreGoodsOut qo) {
        Page<StockDetailGoods> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        Page<StockGoodsOutDetailVo> page1 = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        QueryWrapper<StockDetailGoods> wrapper = new QueryWrapper<>();
        wrapper.likeRight(StringUtils.hasText(qo.getCn()), "cn", qo.getCn());
        wrapper.like(StringUtils.hasText(qo.getGoodsName()), "goods_name", qo.getGoodsName());
        wrapper.ge(StringUtils.hasText(qo.getStartCreateTime()), "create_time", qo.getStartCreateTime());
        wrapper.le(StringUtils.hasText(qo.getEndCreateTime()), "create_time", qo.getEndCreateTime());
        wrapper.eq("type", StockDetailGoods.TYPE_OUT);
        wrapper.eq(StringUtils.hasText(qo.getState()), "state", qo.getState());
        wrapper.eq(StringUtils.hasText(qo.getState1()), "state1", qo.getState1());
        super.page(page, wrapper);
        List<StockDetailGoods> records = page.getRecords();
        if (records == null || records.size() <= 0) {
            page1.setTotal(0L);
        }
        List<StockGoodsOutDetailVo> list = new ArrayList<>();
        for (StockDetailGoods record : records) {
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
        QueryWrapper<GoodsStock> wrapper = new QueryWrapper<>();
        wrapper.gt("residue_num", 0L);
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
        QueryWrapper<GoodsStock> wrapper = new QueryWrapper<>();
        wrapper.eq("goods_id", gid);
        wrapper.gt("residue_num", 0L);
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
        QueryWrapper<GoodsStock> wrapper = new QueryWrapper<>();
        wrapper.eq("store_id", storeId);
        wrapper.gt("residue_num", 0L);
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
        QueryWrapper<GoodsStock> wrapper = new QueryWrapper<>();
        wrapper.eq("goods_id", goodsId);
        wrapper.eq("store_id", storeId);
        GoodsStock goodsStock = goodsStoreService.getOne(wrapper);
        StockGoodsOutDetailVo vo = new StockGoodsOutDetailVo();
        BeanUtils.copyProperties(goodsStock, vo);
        vo.setGoodsNum(goodsStock.getResidueNum());
        Goods goods = goodsService.getById(goodsId);
        vo.setGoodsName(goods.getName());
        return vo;
    }

    @Override
    public void saveOut(StockDetailGoods stockDetailGoods, String token) {
        Employee employee = JSONObject.parseObject(redisTemplateService.getCacheObject(token), Employee.class);
        stockDetailGoods.setType(StockDetailGoods.TYPE_OUT);
        stockDetailGoods.setState1(StockDetailGoods.STATE1_NORMAL);
        stockDetailGoods.setCreateid(employee.getId());
        stockDetailGoods.setCreateby(employee.getNickName());
        stockDetailGoods.setCn(IdWorker.getIdStr());
        stockDetailGoods.setCreateTime(new Date());

        QueryWrapper<GoodsStock> goodsStoreQueryWrapper = new QueryWrapper<GoodsStock>()
                .eq("goods_id", stockDetailGoods.getGoodsId())
                .eq("store_id", stockDetailGoods.getStoreId());

        GoodsStock goodsStock = goodsStoreService.getOne(goodsStoreQueryWrapper);
        long num = goodsStock.getResidueNum() - stockDetailGoods.getGoodsNum();

        UpdateWrapper<GoodsStock> goodsStoreUpdateWrapper = new UpdateWrapper<GoodsStock>()
                .eq("goods_id", stockDetailGoods.getGoodsId())
                .eq("store_id", stockDetailGoods.getStoreId());
        if (StockDetailGoods.STATE_EXPIRY.equals(stockDetailGoods.getState())) {
            //过期处理
            stockDetailGoods.setState(StockDetailGoods.STATE_EXPIRY);
            if (num >= 0) {
                goodsStoreUpdateWrapper.set("residue_num", num);

            } else {
                goodsStoreUpdateWrapper.set("residue_num", 0L);
                stockDetailGoods.setGoodsNum(goodsStock.getResidueNum());
            }
        } else {
            //出库到货架上
            stockDetailGoods.setState(StockDetailGoods.STATE_NORMAL);
            Goods goods = goodsService.getById(stockDetailGoods.getGoodsId());
            UpdateWrapper<Goods> goodsUpdateWrapper = new UpdateWrapper<Goods>().eq("id", goods.getId());
            if (num >= 0) {
                goodsStoreUpdateWrapper.set("residue_num", num);
                goodsUpdateWrapper.set("residue_num", goods.getResidueNum() == null ? stockDetailGoods.getGoodsNum() : goods.getResidueNum() + stockDetailGoods.getGoodsNum());
            } else {
                goodsStoreUpdateWrapper.set("residue_num", 0L);
                goodsUpdateWrapper.set("residue_num", goods.getResidueNum() == null ? goodsStock.getResidueNum() : goods.getResidueNum() + goodsStock.getResidueNum());
                stockDetailGoods.setGoodsNum(goodsStock.getResidueNum());
            }
            goodsService.update(goodsUpdateWrapper);
        }
        goodsStoreService.update(goodsStoreUpdateWrapper);
        super.save(stockDetailGoods);
    }
}
