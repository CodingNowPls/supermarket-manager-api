package com.rabbiter.market.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.rabbiter.market.common.exception.BusinessException;
import com.rabbiter.market.goods.doamin.Goods;
import com.rabbiter.market.goods.doamin.GoodsCategory;
import com.rabbiter.market.goods.mapper.GoodsCategoryMapper;
import com.rabbiter.market.goods.qo.QueryGoodsCategory;
import com.rabbiter.market.goods.service.IGoodsService;
import com.rabbiter.market.goods.service.IGoodsCategoryService;
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

@Service
public class GoodsCategoryServiceImpl extends ServiceImpl<GoodsCategoryMapper, GoodsCategory> implements IGoodsCategoryService {

    @Autowired
    private IGoodsService goodsService;

    @Override
    public void updateGoodsCategory(GoodsCategory goodsCategory) {
        LambdaQueryWrapper<GoodsCategory> queryWrapper = Wrappers.lambdaQuery(GoodsCategory.class)
                .ne(GoodsCategory::getId, goodsCategory.getId())
                .eq(GoodsCategory::getName, goodsCategory.getName())
                .eq(GoodsCategory::getState, goodsCategory.getState());
        GoodsCategory category = super.getOne(queryWrapper);
        if (GoodsCategory.STATE_BAN.equals(goodsCategory.getState())) {
            //查看是否有上架商品正在使用
            LambdaQueryWrapper<Goods> wrapper = Wrappers.lambdaQuery(Goods.class)
                    .eq(Goods::getCategoryId, goodsCategory.getId())
                    .eq(Goods::getState, Goods.STATE_UP);
            List<Goods> list = goodsService.list(wrapper);
            if (list != null && list.size() > 0) {
                throw new BusinessException("该分类正在被某个上架商品使用，请解除关系后，再操作");
            }
            if (category != null) {
                super.removeById(category);
            }
        } else {
            if (category != null) {
                throw new BusinessException("该分类已经存在");
            }
        }

        super.updateById(goodsCategory);
    }

    @Override
    public void deactivate(Long cid) {
        //查看是否有上架商品正在使用
        LambdaQueryWrapper<Goods> wrapper = Wrappers.lambdaQuery(Goods.class)
                .eq(Goods::getCategoryId, cid)
                .eq(Goods::getState, Goods.STATE_UP);
        List<Goods> list = goodsService.list(wrapper);
        if (list != null && list.size() > 0) {
            throw new BusinessException("该分类正在被某个上架商品使用，请解除关系后，再操作");
        }
        /*查看删除中是否有*/
        GoodsCategory goodsCategory = super.getById(cid);
        LambdaQueryWrapper<GoodsCategory> queryWrapper = Wrappers.lambdaQuery(GoodsCategory.class)
                .ne(GoodsCategory::getId, cid)
                .eq(GoodsCategory::getName, goodsCategory.getName())
                .eq(GoodsCategory::getState, GoodsCategory.STATE_BAN);
        GoodsCategory one = super.getOne(queryWrapper);
        if (one != null) {
            super.remove(Wrappers.lambdaQuery(GoodsCategory.class)
                    .eq(GoodsCategory::getName, goodsCategory.getName())
                    .eq(GoodsCategory::getState, GoodsCategory.STATE_BAN));

        }
        super.update(Wrappers.lambdaUpdate(GoodsCategory.class)
                .eq(GoodsCategory::getId, cid)
                .set(GoodsCategory::getState, GoodsCategory.STATE_BAN));
    }

    @Override
    public Page<GoodsCategory> queryPageByQo(QueryGoodsCategory qo) {
        Page<GoodsCategory> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        LambdaQueryWrapper<GoodsCategory> wrapper = Wrappers.lambdaQuery(GoodsCategory.class);
        if (StringUtils.hasText(qo.getName())) {
            wrapper.like(GoodsCategory::getName, qo.getName());
        }
        if (StringUtils.hasText(qo.getState())) {
            wrapper.eq(GoodsCategory::getState, qo.getState());
        }
        return super.page(page, wrapper);
    }

    @Override
    public void saveGoodsCategory(GoodsCategory category) {
        //判断数据库是否保存过这个分类信息
        LambdaQueryWrapper<GoodsCategory> wrapper = Wrappers.lambdaQuery(GoodsCategory.class);
        if (StringUtils.hasText(category.getName())) {
            wrapper.eq(GoodsCategory::getName, category.getName());
        }
        wrapper.eq(GoodsCategory::getState, GoodsCategory.STATE_NORMAL);
        GoodsCategory category1 = super.getOne(wrapper);
        if (category1 != null) {
            throw new BusinessException("该分类已被创建");
        }
        category.setState(GoodsCategory.STATE_NORMAL);
        super.save(category);

    }

    @Override
    public List<Map<String, Object>> getNormalCategoryAll() {
        List<Map<String, Object>> list = new ArrayList<>();
        LambdaQueryWrapper<GoodsCategory> lqw = Wrappers.lambdaQuery(GoodsCategory.class)
                .eq(GoodsCategory::getState, GoodsCategory.STATE_NORMAL);
        List<GoodsCategory> categories = super.list(lqw);
        for (GoodsCategory category : categories) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", category.getId());
            map.put("name", category.getName());
            list.add(map);
        }
        return list;
    }
}
