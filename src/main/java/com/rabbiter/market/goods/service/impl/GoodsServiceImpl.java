package com.rabbiter.market.goods.service.impl;

import com.rabbiter.market.common.exception.BusinessException;
import com.rabbiter.market.common.redis.constants.RedisKeys;
import com.rabbiter.market.common.redis.service.RedisTemplateService;
import com.rabbiter.market.goods.doamin.Goods;
import com.rabbiter.market.goods.doamin.GoodsCategory;
import com.rabbiter.market.inventory.domain.*;
import com.rabbiter.market.inventory.domain.Warehouse;
import com.rabbiter.market.person.domain.Employee;
import com.rabbiter.market.goods.mapper.GoodsMapper;
import com.rabbiter.market.goods.qo.QueryGoods;
import com.rabbiter.market.goods.qo.QueryGoodsStore;
import com.rabbiter.market.goods.qo.QueryStatisticSale;
import com.rabbiter.market.inventory.qo.QueryNoticeIn;
import com.rabbiter.market.inventory.qo.QueryNoticeOut;
import com.rabbiter.market.goods.service.IGoodsService;
import com.rabbiter.market.goods.service.IGoodsCategoryService;
import com.rabbiter.market.inventory.service.IStockGoodsDetailService;
import com.rabbiter.market.inventory.service.IGoodsStockService;
import com.rabbiter.market.inventory.service.IWarehouseService;
import com.rabbiter.market.goods.vo.NoticeInNotNormalVo;
import com.rabbiter.market.goods.vo.GoodsListVo;
import com.rabbiter.market.goods.vo.GoodsStockVo;
import com.rabbiter.market.sale.vo.SaleGoodsVo;
import com.rabbiter.market.sale.vo.SalesStatisticsVo;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {
    @Autowired
    private RedisTemplateService redisTemplateService;
    @Autowired
    private IGoodsCategoryService goodsCategoryService;

    @Autowired
    private IWarehouseService storeService;
    @Autowired
    private IStockGoodsDetailService detailStoreGoodsService;
    @Autowired
    private IGoodsStockService goodsStoreService;
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public Page<GoodsListVo> queryPageByQo(QueryGoods qo) {
        Page<GoodsListVo> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        ArrayList<GoodsListVo> volists = new ArrayList<>();
        Page<Goods> goodsPage = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        QueryWrapper<Goods> wrapper = new QueryWrapper<Goods>()
                .eq(qo.getId() != null, "id", qo.getId())
                .eq(qo.getSellPrice() != null, "sell_price", qo.getSellPrice())
                .like(StringUtils.hasText(qo.getName()), "name", qo.getName())
                .eq(qo.getCategoryId() != null, "category_id", qo.getCategoryId())
                .eq(StringUtils.hasText(qo.getState()), "state", qo.getState())
                .ge(StringUtils.hasText(qo.getOperateStartTime()), "update_time", qo.getOperateStartTime())
                .le(StringUtils.hasText(qo.getOperateEndTime()), "update_time", qo.getOperateEndTime());
        super.page(goodsPage, wrapper);
        for (Goods record : goodsPage.getRecords()) {
            GoodsListVo vo = new GoodsListVo();
            BeanUtils.copyProperties(record, vo);
            Long residueNum = storeService.getResidueNumByGoodsId(record.getId());
            vo.setResidueStoreNum(residueNum);
            volists.add(vo);
        }
        page.setRecords(volists);
        page.setTotal(goodsPage.getTotal());
        return page;
    }

    @Override
    public void saveGoods(Goods goods) {
        String token = HttpRequestUtil.getToken();
        Employee employee = JSONObject.parseObject(redisTemplateService.getCacheObject(token), Employee.class);
        goods.setState(Goods.STATE_UP);
        goods.setCreateby(employee.getNickName());
        goods.setUpdateby(employee.getNickName());
        goods.setCreateTime(new Date());
        goods.setUpdateTime(new Date());
        if (goods.getCategoryId() != null) {
            /*从缓存中获取分类的信息*/
            if (redisTemplateService.hasKey(RedisKeys.GOODS_CATEGORY.join())) {
                Map<String, Object> categoryCache = redisTemplateService.getCacheMap(RedisKeys.GOODS_CATEGORY.join());
                GoodsCategory category = (GoodsCategory) categoryCache.get(goods.getCategoryId().toString());
                if (category != null) {
                    goods.setCategoryName(category.getName());
                }
            } else {
                GoodsCategory category = goodsCategoryService.getById(goods.getCategoryId());
                if (category != null) {
                    goods.setCategoryName(category.getName());
                }
            }
        }
        super.save(goods);
    }

    @Transactional
    @Override
    public void upOrdown(Long gid, String state) {
        String token = HttpRequestUtil.getToken();
        UpdateWrapper<Goods> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", gid);
        if (Goods.STATE_UP.equals(state)) {
            Employee employee = JSONObject.parseObject(redisTemplateService.getCacheObject(token), Employee.class);
            wrapper.set("state", Goods.STATE_DOWN);
            Goods goods = super.getById(gid);
            QueryWrapper<GoodsStock> goodsStoreQueryWrapper = new QueryWrapper<GoodsStock>().eq("goods_id", gid);
            List<GoodsStock> list = goodsStoreService.list(goodsStoreQueryWrapper);
            for (GoodsStock goodsStock : list) {
                GoodsStockDetail goodsStockDetail = new GoodsStockDetail();
                goodsStockDetail.setCreateid(employee.getId());
                goodsStockDetail.setCreateby(employee.getNickName());
                goodsStockDetail.setCreateTime(new Date());
                goodsStockDetail.setGoodsId(gid);
                goodsStockDetail.setGoodsName(goods.getName());
                goodsStockDetail.setType(GoodsStockDetail.TYPE_IN);
                goodsStockDetail.setState1(GoodsStockDetail.STATE1_UNTREATED);
                goodsStockDetail.setState(GoodsStockDetail.STATE_DOWN);
                goodsStockDetail.setCn(IdWorker.getIdStr());
                goodsStockDetail.setInfo(goods.getName() + "下架处理");
                goodsStockDetail.setGoodsNum(goodsStock.getResidueNum());
                goodsStockDetail.setUntreatedNum(goodsStock.getResidueNum());
                goodsStockDetail.setStoreId(goodsStock.getStoreId());
                detailStoreGoodsService.save(goodsStockDetail);
            }
        } else {
            wrapper.set("residue_num", 0);
            wrapper.set("state", Goods.STATE_UP);
            QueryWrapper<GoodsStockDetail> queryWrapper = new QueryWrapper<GoodsStockDetail>().eq("goods_id", gid)
                    .eq("state", GoodsStockDetail.STATE_DOWN)
                    .eq("state1", GoodsStockDetail.STATE1_UNTREATED);
            detailStoreGoodsService.remove(queryWrapper);
        }
        super.update(wrapper);

    }

    @Override
    public void updateGoods(Goods goods) {
        String token = HttpRequestUtil.getToken();
        Employee employee = JSONObject.parseObject(redisTemplateService.getCacheObject(token), Employee.class);
        goods.setUpdateby(employee.getNickName());
        goods.setUpdateTime(new Date());
        if (goods.getCategoryId() != null) {
            /*从缓存中获取分类的信息*/
            if (redisTemplateService.hasKey(RedisKeys.GOODS_CATEGORY.join())) {
                Map<String, Object> categoryCache = redisTemplateService.getCacheMap(RedisKeys.GOODS_CATEGORY.join());
                GoodsCategory category = (GoodsCategory) categoryCache.get(goods.getCategoryId().toString());
                if (category != null) {
                    goods.setCategoryName(category.getName());
                }
            } else {
                GoodsCategory category = goodsCategoryService.getById(goods.getCategoryId());
                if (category != null) {
                    goods.setCategoryName(category.getName());
                }
            }
        }
        super.updateById(goods);
    }

    @Override
    public List<Map<String, Object>> selected_goodsAll() {
        QueryWrapper<Goods> wrapper = new QueryWrapper<Goods>().eq("state", Goods.STATE_UP);
        List<Goods> list = super.list(wrapper);
        if (list == null && list.size() == 0) {
            return null;
        }
        List<Map<String, Object>> listVo = new ArrayList<>();
        for (Goods goods : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", goods.getId());
            map.put("name", goods.getName());
            listVo.add(map);
        }

        return listVo;
    }

    @Override
    public List<Map<String, Object>> selected_storeAll() {
        List<Map<String, Object>> list = new ArrayList<>();
        QueryWrapper<Warehouse> wrapper = new QueryWrapper<Warehouse>().eq("state", Warehouse.STATE_NORMAL);
        List<Warehouse> list1 = storeService.list(wrapper);
        if (list1 != null && list1.size() > 0) {
            for (Warehouse warehouse : list1) {
                Map<String, Object> map = new HashMap<>();
                map.put("id", warehouse.getId());
                map.put("name", warehouse.getName());
                list.add(map);
            }
        }
        return list;
    }

    @Override
    public void returnGoods(GoodsStockDetail goodsStockDetail) {
        String token = HttpRequestUtil.getToken();
        Employee employee = JSONObject.parseObject(redisTemplateService.getCacheObject(token), Employee.class);
        Goods goods = super.getById(goodsStockDetail.getGoodsId());

        /*补全入库订单信息*/
        goodsStockDetail.setCn(IdWorker.getIdStr());
        goodsStockDetail.setCreateby(employee.getNickName());
        goodsStockDetail.setCreateid(employee.getId());
        goodsStockDetail.setType(GoodsStockDetail.TYPE_IN);
        if (GoodsStockDetail.STATE_EXPIRY.equals(goodsStockDetail.getState())) {
            //如果是过期，将入库订单的state1修改成2：待处理的状态
            goodsStockDetail.setState1(GoodsStockDetail.STATE1_UNTREATED);
        } else {
            goodsStockDetail.setState1(GoodsStockDetail.STATE1_NORMAL);
        }


        /*获取仓库的信息*/
        QueryWrapper<GoodsStock> goodsStoreQueryWrapper = new QueryWrapper<GoodsStock>()
                .eq("goods_id", goodsStockDetail.getGoodsId())
                .eq("store_id", goodsStockDetail.getStoreId());
        GoodsStock goodsStock = goodsStoreService.getOne(goodsStoreQueryWrapper);
        if (goodsStock == null) {
            goodsStock = new GoodsStock();
            goodsStock.setGoodsId(goodsStockDetail.getGoodsId());
            goodsStock.setStoreId(goodsStockDetail.getStoreId());
            Warehouse warehouse = storeService.getById(goodsStockDetail.getStoreId());
            goodsStock.setStoreName(warehouse.getName());
            goodsStock.setInNum(0L);
            goodsStock.setResidueNum(0L);
            goodsStoreService.save(goodsStock);
        }
        long num = goods.getResidueNum() - goodsStockDetail.getGoodsNum();
        if (num >= 0) {
            //货架还有商品数量
            /*更改商品信息*/
            UpdateWrapper<Goods> goodsUpdateWrapper = new UpdateWrapper<Goods>()
                    .set("residue_num", num)
                    .eq("id", goodsStockDetail.getGoodsId());
            super.update(goodsUpdateWrapper);
            /*更改商品库存信息*/
            UpdateWrapper<GoodsStock> goodsStoreUpdateWrapper = new UpdateWrapper<GoodsStock>()
                    .set("residue_num", goodsStock.getResidueNum() + goodsStockDetail.getGoodsNum())
                    .eq("goods_id", goodsStockDetail.getGoodsId())
                    .eq("store_id", goodsStockDetail.getStoreId());
            goodsStoreService.update(goodsStoreUpdateWrapper);
            goodsStockDetail.setUntreatedNum(goodsStockDetail.getGoodsNum());

        } else {
            //货架没有商品数量
            /*更改商品信息*/
            UpdateWrapper<Goods> goodsUpdateWrapper = new UpdateWrapper<Goods>()
                    .set("residue_num", 0)
                    .eq("id", goodsStockDetail.getGoodsId());
            super.update(goodsUpdateWrapper);
            /*更改商品库存信息*/
            UpdateWrapper<GoodsStock> goodsStoreUpdateWrapper = new UpdateWrapper<GoodsStock>()
                    .set("residue_num", goodsStock.getResidueNum() + goods.getResidueNum())
                    .eq("goods_id", goodsStockDetail.getGoodsId())
                    .eq("store_id", goodsStockDetail.getStoreId());
            goodsStoreService.update(goodsStoreUpdateWrapper);
            goodsStockDetail.setGoodsNum(goods.getResidueNum());
            goodsStockDetail.setUntreatedNum(goods.getResidueNum());
        }
        detailStoreGoodsService.save(goodsStockDetail);
    }

    @Override
    public Page<GoodsStockVo> queryPageGoodsStore(QueryGoodsStore qo) {
        Page<GoodsStockVo> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        Page<Goods> goodsPage = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        QueryWrapper<Goods> wrapper = new QueryWrapper<Goods>().eq("state", Goods.STATE_UP)
                .like(StringUtils.hasText(qo.getName()), "name", qo.getName());
        super.page(goodsPage, wrapper);
        if (goodsPage.getTotal() <= 0) {
            page.setRecords(new ArrayList<>());
            page.setTotal(0);
            return page;
        }
        List<GoodsStockVo> list = new ArrayList<>();
        for (Goods record : goodsPage.getRecords()) {
            GoodsStockVo vo = new GoodsStockVo();
            BeanUtils.copyProperties(record, vo);
            list.add(vo);
        }
        page.setTotal(goodsPage.getTotal());
        page.setRecords(list);
        return page;
    }

    @Override
    public GoodsStockVo queryGoodsStoreById(Long id) {
        GoodsStockVo vo = new GoodsStockVo();
        Goods goods = super.getById(id);
        BeanUtils.copyProperties(goods, vo);
        return vo;
    }

    @Override
    public void updateInventory(GoodsStockVo vo) {
        if (vo.getInventory() == null) {
            vo.setInventory(0L);
        }
        if (vo.getShelves() == null) {
            vo.setShelves(0L);
        }
        UpdateWrapper<Goods> updateWrapper = new UpdateWrapper<Goods>()
                .set("inventory", vo.getInventory())
                .set("shelves", vo.getShelves())
                .eq("id", vo.getId());
        super.update(updateWrapper);

    }

    @Override
    public Page<NoticeIn> queryPageNoticeIn(QueryNoticeIn qo) {
        Page<NoticeIn> noticeInPage = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        List<NoticeIn> list = new ArrayList<>();
        int start = (qo.getCurrentPage() - 1) * qo.getPageSize();
        Map<String, Object> map = new HashMap<>();
        map.put("start", start);
        map.put("size", qo.getPageSize());
        if (StringUtils.hasLength(qo.getName())) {
            map.put("name", qo.getName());
        }
        int totalCount = goodsMapper.getNoticeInTotalCount(map);
        list = goodsMapper.getNoticePageList(map);
        noticeInPage.setTotal(totalCount);
        noticeInPage.setRecords(list);
        return noticeInPage;
    }

    @Override
    public Page<NoticeOut> queryPageNoticeOut_shelves(QueryNoticeOut qo) {
        Page<NoticeOut> noticeOutPage = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        List<NoticeOut> list = new ArrayList<>();
        int start = (qo.getCurrentPage() - 1) * qo.getPageSize();
        Map<String, Object> map = new HashMap<>();
        map.put("start", start);
        map.put("size", qo.getPageSize());
        if (StringUtils.hasLength(qo.getName())) {
            map.put("name", qo.getName());
        }
        int totalCount = goodsMapper.getNoticeOutShelvesTotalCount(map);
        list = goodsMapper.getNoticeShelvesPageList(map);
        noticeOutPage.setTotal(totalCount);
        noticeOutPage.setRecords(list);
        return noticeOutPage;
    }

    @Override
    public void saveOut_shelves(GoodsStockDetail goodsStockDetail) {
        String token = HttpRequestUtil.getToken();
        Employee employee = JSONObject.parseObject(redisTemplateService.getCacheObject(token), Employee.class);
        QueryWrapper<GoodsStock> detailStoreGoodsQueryWrapper = new QueryWrapper<GoodsStock>().eq("goods_id", goodsStockDetail.getGoodsId())
                .eq("store_id", goodsStockDetail.getStoreId());
        GoodsStock goodsStock = goodsStoreService.getOne(detailStoreGoodsQueryWrapper);
        if (goodsStock == null || goodsStock.getResidueNum() == null || goodsStock.getResidueNum() == 0) {
            throw new BusinessException("出库失败，库存中没有该商品的库存");
        }
        /*补全出库单的信息*/
        goodsStockDetail.setCn(IdWorker.getIdStr());
        goodsStockDetail.setCreateby(employee.getNickName());
        goodsStockDetail.setCreateid(employee.getId());
        goodsStockDetail.setType(GoodsStockDetail.TYPE_OUT);
        goodsStockDetail.setState1(GoodsStockDetail.STATE1_NORMAL);
        long num = goodsStock.getResidueNum() - goodsStockDetail.getGoodsNum();
        Goods goods = super.getById(goodsStockDetail.getGoodsId());
        if (num >= 0) {
            /*修改货架商品数量*/
            UpdateWrapper<Goods> goodsUpdateWrapper = new UpdateWrapper<Goods>()
                    .set("residue_num", goods.getResidueNum() == null ? goodsStockDetail.getGoodsNum() : goods.getResidueNum() + goodsStockDetail.getGoodsNum())
                    .eq("id", goodsStockDetail.getGoodsId());
            super.update(goodsUpdateWrapper);
            /*修改商品库存数量*/
            UpdateWrapper<GoodsStock> goodsStoreUpdateWrapper = new UpdateWrapper<GoodsStock>()
                    .set("residue_num", goodsStock.getResidueNum() - goodsStockDetail.getGoodsNum())
                    .eq("goods_id", goodsStockDetail.getGoodsId())
                    .eq("store_id", goodsStockDetail.getStoreId());
            goodsStoreService.update(goodsStoreUpdateWrapper);
            /*添加出库记录*/
            detailStoreGoodsService.save(goodsStockDetail);
        } else {
            /*修改货架商品数量*/
            UpdateWrapper<Goods> goodsUpdateWrapper = new UpdateWrapper<Goods>()
                    .set("residue_num", goods.getResidueNum() == null ? goodsStock.getResidueNum() : goods.getResidueNum() + goodsStock.getResidueNum())
                    .eq("id", goodsStockDetail.getGoodsId());
            super.update(goodsUpdateWrapper);
            /*修改商品库存数量*/
            UpdateWrapper<GoodsStock> goodsStoreUpdateWrapper = new UpdateWrapper<GoodsStock>()
                    .set("residue_num", 0L)
                    .eq("goods_id", goodsStockDetail.getGoodsId())
                    .eq("store_id", goodsStockDetail.getStoreId());
            goodsStoreService.update(goodsStoreUpdateWrapper);
            /*添加出库记录*/
            goodsStockDetail.setGoodsNum(goodsStock.getResidueNum());
            detailStoreGoodsService.save(goodsStockDetail);
        }


    }

    @Override
    public SalesStatisticsVo queryPageStatisticSaleByQo(QueryStatisticSale qo) {
        Long total = goodsMapper.queryPageStatisticSaleByQo(qo.getName());
        SalesStatisticsVo vo = new SalesStatisticsVo();
        vo.setTotal(total);
        QueryWrapper<Goods> wrapper = new QueryWrapper<Goods>().eq("state", Goods.STATE_UP)
                .like(StringUtils.hasText(qo.getName()), "name", qo.getName());
        Page<Goods> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        super.page(page, wrapper);
        Page<SaleGoodsVo> saleGoodsVoPage = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        saleGoodsVoPage.setTotal(page.getTotal());
        List<SaleGoodsVo> saleGoodsVos = new ArrayList<>();
        for (Goods record : page.getRecords()) {
            SaleGoodsVo goodsVo = new SaleGoodsVo();
            goodsVo.setGoodsId(record.getId());
            goodsVo.setGoodsName(record.getName());
            goodsVo.setSalesVolume(record.getSalesVolume());
            goodsVo.setPercentage(total);
            goodsVo.setCoverUrl(record.getCoverUrl());
            saleGoodsVos.add(goodsVo);
        }
        saleGoodsVoPage.setRecords(saleGoodsVos);
        vo.setVos(saleGoodsVoPage);
        return vo;
    }

    @Override
    public Page<NoticeInNotNormalVo> queryPageNoticeOut_untreated(QueryNoticeOut qo) {
        Page<NoticeInNotNormalVo> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        List<NoticeInNotNormalVo> vos = new ArrayList<>();
        QueryWrapper<GoodsStockDetail> queryWrapper = new QueryWrapper<GoodsStockDetail>().eq("state1", GoodsStockDetail.STATE1_UNTREATED);
        queryWrapper.eq(StringUtils.hasText(qo.getState()), "state", qo.getState());
        queryWrapper.like(StringUtils.hasText(qo.getName()), "goods_name", qo.getName());
        queryWrapper.eq("type", GoodsStockDetail.TYPE_IN);
        queryWrapper.orderByDesc("create_time");
        List<GoodsStockDetail> list = detailStoreGoodsService.list(queryWrapper);
        for (GoodsStockDetail goodsStockDetail : list) {
            NoticeInNotNormalVo vo = new NoticeInNotNormalVo();
            vo.setCn(goodsStockDetail.getCn());
            vo.setCreateTime(goodsStockDetail.getCreateTime());
            vo.setGoodsId(goodsStockDetail.getGoodsId());
            vo.setGoodsName(goodsStockDetail.getGoodsName());
            vo.setUntreatedNum(goodsStockDetail.getUntreatedNum());
            vo.setState(goodsStockDetail.getState());
            vo.setStoreId(goodsStockDetail.getStoreId());
            Warehouse warehouse = storeService.getById(goodsStockDetail.getStoreId());
            vo.setStoreName(warehouse.getName());
            Goods goods = super.getById(goodsStockDetail.getGoodsId());
            vo.setCoverUrl(goods.getCoverUrl());
            vos.add(vo);

        }
        page.setRecords(vos);
        return page;
    }

    @Override
    public void resolveOutUntreatedForm(NoticeInNotNormalVo vo) {
        String token = HttpRequestUtil.getToken();
        Employee employee = JSONObject.parseObject(redisTemplateService.getCacheObject(token), Employee.class);
        QueryWrapper<GoodsStockDetail> queryWrapper = new QueryWrapper<GoodsStockDetail>()
                .eq("cn", vo.getCn())
                .eq("state1", GoodsStockDetail.STATE1_UNTREATED);
        GoodsStockDetail goodsStockDetail = detailStoreGoodsService.getOne(queryWrapper);
        if (goodsStockDetail == null) {
            throw new BusinessException("该订单已被处理");
        }

        long num = goodsStockDetail.getUntreatedNum() - vo.getUntreatedNum();
        QueryWrapper<GoodsStock> goodsStoreQueryWrapper = new QueryWrapper<GoodsStock>()
                .eq("goods_id", vo.getGoodsId())
                .eq("store_id", vo.getStoreId());
        GoodsStock goodsStock = goodsStoreService.getOne(goodsStoreQueryWrapper);
        if (num > 0) {
            //未处理完毕
            UpdateWrapper<GoodsStockDetail> updateWrapper = new UpdateWrapper<GoodsStockDetail>()
                    .eq("cn", goodsStockDetail.getCn())
                    .set("untreated_num", num);
            detailStoreGoodsService.update(updateWrapper);
            //改变库存
            UpdateWrapper<GoodsStock> goodsStoreUpdateWrapper = new UpdateWrapper<GoodsStock>()
                    .eq("goods_id", vo.getGoodsId())
                    .eq("store_id", vo.getStoreId())
                    .set("residue_num", goodsStock.getResidueNum() - vo.getUntreatedNum());
            goodsStoreService.update(goodsStoreUpdateWrapper);
        } else {
            //处理完毕
            UpdateWrapper<GoodsStockDetail> updateWrapper = new UpdateWrapper<GoodsStockDetail>()
                    .eq("cn", goodsStockDetail.getCn())
                    .set("untreated_num", 0L)
                    .set("state1", GoodsStockDetail.STATE1_NORMAL)
                    .set("createid", employee.getId())
                    .set("createby", employee.getNickName())
                    .set("create_time", new Date())
                    .set("type", GoodsStockDetail.TYPE_OUT);
            detailStoreGoodsService.update(updateWrapper);
            //改变库存
            UpdateWrapper<GoodsStock> goodsStoreUpdateWrapper = new UpdateWrapper<GoodsStock>()
                    .eq("goods_id", vo.getGoodsId())
                    .eq("store_id", vo.getStoreId())
                    .set("residue_num", 0L);
            goodsStoreService.update(goodsStoreUpdateWrapper);
        }

    }

}
