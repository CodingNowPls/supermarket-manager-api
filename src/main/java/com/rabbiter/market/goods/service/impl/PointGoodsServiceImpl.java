package com.rabbiter.market.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.rabbiter.market.common.exception.BusinessException;
import com.rabbiter.market.common.redis.service.RedisTemplateService;
import com.rabbiter.market.goods.doamin.Goods;
import com.rabbiter.market.goods.doamin.PointGoods;
import com.rabbiter.market.person.domain.Employee;
import com.rabbiter.market.goods.mapper.PointProductsMapper;
import com.rabbiter.market.goods.qo.QueryPointProducts;
import com.rabbiter.market.goods.service.IGoodsService;
import com.rabbiter.market.goods.service.IPointGoodsService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rabbiter.market.util.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class PointGoodsServiceImpl extends ServiceImpl<PointProductsMapper, PointGoods> implements IPointGoodsService {
    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private RedisTemplateService redisTemplateService;

    @Override
    public Page<PointGoods> queryPageByQo(QueryPointProducts qo) {
        Page<PointGoods> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        LambdaQueryWrapper<PointGoods> wrapper = Wrappers.lambdaQuery(PointGoods.class);
        if (StringUtils.hasText(qo.getName())) {
            wrapper.like(PointGoods::getGoodsName, qo.getName());
        }
        super.page(page, wrapper);
        return page;
    }

    @Override
    public List<Map<String, Object>> queryOptionGoods() {
        List<PointGoods> list = super.list();
        LambdaQueryWrapper<Goods> goodsQueryWrapper = Wrappers.lambdaQuery(Goods.class);
        goodsQueryWrapper.eq(Goods::getState, Goods.STATE_UP);
        Set<Long> productGoodsIds = new HashSet<>();
        if (CollectionUtils.isNotEmpty(list)) {
            list.forEach(item -> productGoodsIds.add(item.getGoodsId()));
            if (productGoodsIds.size() > 0) {
                goodsQueryWrapper.notIn(Goods::getId, productGoodsIds);
            }
        }

        List<Goods> goods = goodsService.list(goodsQueryWrapper);
        ArrayList<Map<String, Object>> options = new ArrayList<>();
        goods.forEach(item -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", item.getId());
            map.put("name", item.getName());
            options.add(map);
        });

        return options;
    }

    @Override
    public void savePointGoods(PointGoods pointGoods) {
        String token = HttpRequestUtil.getToken();
        Employee employee = JSONObject.parseObject(redisTemplateService.getCacheObject(token), Employee.class);
        LambdaQueryWrapper<PointGoods> wrapper = Wrappers.lambdaQuery(PointGoods.class)
                .eq(PointGoods::getGoodsId, pointGoods.getGoodsId());
        PointGoods one = super.getOne(wrapper);
        if (one != null) {
            throw new BusinessException("该商品已经是积分商品");
        }
        pointGoods.setUpdateby(employee.getNickName());
        pointGoods.setUpdateTime(new Date());
        pointGoods.setUpdateId(employee.getId());
        pointGoods.setState(PointGoods.STATE_NORMAL);
        super.save(pointGoods);
    }

    @Override
    public void updatePointGoods(PointGoods pointGoods) {
        String token = HttpRequestUtil.getToken();
        Employee employee = JSONObject.parseObject(redisTemplateService.getCacheObject(token), Employee.class);
        pointGoods.setUpdateby(employee.getNickName());
        pointGoods.setUpdateTime(new Date());
        pointGoods.setUpdateId(employee.getId());
        LambdaUpdateWrapper<PointGoods> updateWrapper = Wrappers.lambdaUpdate(PointGoods.class)
                .set(PointGoods::getIntegral, pointGoods.getIntegral())
                .eq(PointGoods::getGoodsId, pointGoods.getGoodsId());
        super.update(updateWrapper);
    }

    @Override
    public void del(Long id) {
        // 直接物理删除
        LambdaQueryWrapper<PointGoods> lqw = Wrappers.lambdaQuery(PointGoods.class);
        lqw.eq(PointGoods::getGoodsId, id);
        this.baseMapper.delete(lqw);
//        UpdateWrapper<PointProducts> updateWrapper = new UpdateWrapper<PointProducts>()
//                .set("state", PointProducts.STATE_DEL)
//                .eq("goods_id", id);
//        super.update(updateWrapper);
    }
}
