package com.rabbiter.market.inventory.service.impl;

import com.rabbiter.market.common.exception.BusinessException;
import com.rabbiter.market.common.redis.service.RedisTemplateService;
import com.rabbiter.market.goods.doamin.Goods;
import com.rabbiter.market.inventory.domain.GoodsStock;
import com.rabbiter.market.inventory.domain.Warehouse;
import com.rabbiter.market.inventory.mapper.GoodsStockMapper;
import com.rabbiter.market.inventory.qo.QueryDetailStorageSituation;
import com.rabbiter.market.inventory.qo.QueryStorageSituation;
import com.rabbiter.market.goods.service.IGoodsService;
import com.rabbiter.market.inventory.service.IStockGoodsDetailService;
import com.rabbiter.market.inventory.service.IGoodsStockService;
import com.rabbiter.market.inventory.service.IWarehouseService;
import com.rabbiter.market.sale.vo.DetailStorageSituationVo;
import com.rabbiter.market.sale.vo.StorageSituationVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 *
 */
@Service
@Transactional
public class GoodsStockServiceImpl extends ServiceImpl<GoodsStockMapper, GoodsStock> implements IGoodsStockService {

    @Autowired
    private GoodsStockMapper goodsStockMapper;
    @Autowired
    private IStockGoodsDetailService detailStoreGoodsService;
    @Autowired
    private RedisTemplateService redisTemplateService;
    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private IWarehouseService storeService;

    @Override
    public Long storeUsed(Long id) {
        return goodsStockMapper.storeUsed(id);
    }

    @Override
    public Long getResidueNumByGoodsId(Long id) {
        return goodsStockMapper.getResidueNumByGoodsId(id);
    }

    @Override
    public void goodsInStore(Long goodsId, Long goodsNum, Long storeId) {
        QueryWrapper<GoodsStock> wrapper = new QueryWrapper<GoodsStock>().eq("goods_id", goodsId).eq("store_id", storeId);
        GoodsStock goodsStock1 = super.getOne(wrapper);
        if (goodsStock1 != null) {
            goodsStockMapper.goodsInStore(goodsId, goodsNum, storeId);
        } else {
            GoodsStock goodsStock = new GoodsStock();
            goodsStock.setGoodsId(goodsId);
            goodsStock.setInNum(goodsNum);
            goodsStock.setResidueNum(goodsNum);
            goodsStock.setStoreId(storeId);
            Warehouse one = storeService.getById(storeId);
            goodsStock.setStoreName(one.getName());
            super.save(goodsStock);
        }
    }

    @Override
    public void goodsOutStore(Long goodsId, Long goodsNum, Long storeId) {
        goodsStockMapper.goodsOutStore(goodsId, goodsNum, storeId);
    }

    @Override
    public Map<String, Object> queryPageStorageSituationByQo(QueryStorageSituation qo) {
        HashMap<String, Object> map = new HashMap<>();
        Long totalStoreNum = goodsStockMapper.totalStoreNum();
        map.put("totalStoreNum", totalStoreNum != null ? totalStoreNum : 0L);
        Page<GoodsStock> goodsStorePage = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        QueryWrapper<GoodsStock> goodsStoreQueryWrapper = new QueryWrapper<GoodsStock>().select("store_id,store_name,SUM(residue_num) residue_num")
                .like(StringUtils.hasText(qo.getName()), "store_name", qo.getName())
                .groupBy("store_id", "store_name");
        super.page(goodsStorePage, goodsStoreQueryWrapper);
        Page<StorageSituationVo> situationVoPage = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        List<StorageSituationVo> vos = new ArrayList<>();
        for (GoodsStock record : goodsStorePage.getRecords()) {
            StorageSituationVo vo = new StorageSituationVo();
            vo.setStoreId(record.getStoreId());
            vo.setStoreName(record.getStoreName());
            vo.setResidueNum(record.getResidueNum());
            vos.add(vo);
        }
        situationVoPage.setRecords(vos);
        situationVoPage.setTotal(goodsStorePage.getTotal());
        map.put("page", situationVoPage);
        return map;
    }

    @Override
    public Map<String, Object> queryStoreGoodsByStoreId(QueryDetailStorageSituation qo) {
        Map<String, Object> map = new HashMap<>();
        Long totalStoreNum1 = goodsStockMapper.getTotalStoreNum1(qo.getStoreId());
        map.put("totalStoreNum1", totalStoreNum1);//该仓库的存储量

        QueryWrapper<GoodsStock> wrapper = new QueryWrapper<GoodsStock>()
                .gt("residue_num", 0)
                .eq("store_id", qo.getStoreId());
        List<GoodsStock> list = super.list(wrapper);
        Set<Long> goodsIds = new HashSet<>();
        for (GoodsStock goodsStock : list) {
            goodsIds.add(goodsStock.getGoodsId());
        }
        if (goodsIds.size() <= 0) {
            throw new BusinessException("该仓库没有存放任何的商品");
        }
        List<Goods> goodsList = goodsService.listByIds(goodsIds);
        List<Map<String, Object>> optionsStoreGoods = new ArrayList<>();
        for (Goods goods : goodsList) {
            Map<String, Object> options = new HashMap<>();
            options.put("id", goods.getId());
            options.put("name", goods.getName());
            optionsStoreGoods.add(options);
        }
        map.put("optionsStoreGoods", optionsStoreGoods);//选择框
        Page<GoodsStock> goodsStorePage = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        QueryWrapper<GoodsStock> goodsStoreQueryWrapper = new QueryWrapper<GoodsStock>()
                .eq("store_id", qo.getStoreId())
                .gt("residue_num", 0)
                .eq(qo.getId() != null, "goods_id", qo.getId());
        super.page(goodsStorePage, goodsStoreQueryWrapper);

        Page<DetailStorageSituationVo> voPage = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        voPage.setTotal(goodsStorePage.getTotal());
        List<DetailStorageSituationVo> detailStorageSituationVoList = new ArrayList<>();
        for (GoodsStock record : goodsStorePage.getRecords()) {
            DetailStorageSituationVo vo = new DetailStorageSituationVo();
            vo.setGoodsId(record.getGoodsId());
            vo.setResidueNum(record.getResidueNum());
            vo.setPercentage(totalStoreNum1);
            vo.setGoodsName(goodsService.getById(record.getGoodsId()).getName());
            detailStorageSituationVoList.add(vo);
        }
        voPage.setRecords(detailStorageSituationVoList);
        map.put("vos", voPage);//表格数据
        return map;
    }
}
