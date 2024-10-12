package com.rabbiter.market.sale.service.impl;

import com.rabbiter.market.common.redis.service.RedisTemplateService;
import com.rabbiter.market.goods.doamin.Goods;
import com.rabbiter.market.goods.doamin.PointProducts;
import com.rabbiter.market.member.domain.Member;
import com.rabbiter.market.person.domain.Employee;
import com.rabbiter.market.sale.domain.PointRedemptionHistory;
import com.rabbiter.market.sale.mapper.ExchangePointProductsMapper;
import com.rabbiter.market.inventory.qo.QueryExchangePointProductsRecords;
import com.rabbiter.market.goods.service.IGoodsService;
import com.rabbiter.market.goods.service.IPointProductsService;
import com.rabbiter.market.member.service.IMemberService;
import com.rabbiter.market.sale.service.IExchangePointProductsService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class ExchangePointProductsServiceImpl extends ServiceImpl<ExchangePointProductsMapper, PointRedemptionHistory> implements IExchangePointProductsService {
    @Autowired
    private IMemberService memberService;
    @Autowired
    private IPointProductsService pointProductsService;
    @Autowired
    private RedisTemplateService redisTemplateService;
    @Autowired
    private IGoodsService goodsService;

    @Override
    public List<Map<String, Object>> queryPointProductBymemberId(Long memberId) {
        Member member = memberService.getById(memberId);

        QueryWrapper<PointProducts> pointProductsQueryWrapper = new QueryWrapper<PointProducts>();
        if (memberId == null) {
            pointProductsQueryWrapper.gt("integral", 0L);
        } else {
            pointProductsQueryWrapper.le("integral", member.getIntegral());
        }

        List<PointProducts> list = pointProductsService.list(pointProductsQueryWrapper);
        List<Map<String, Object>> mapArrayList = new ArrayList<>();
        for (PointProducts pointProducts : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", pointProducts.getGoodsId());
            map.put("name", pointProducts.getGoodsName());
            mapArrayList.add(map);
        }
        return mapArrayList;
    }

    @Override
    public PointProducts queryPointProductByGoodsId(Long goodsId) {
        PointProducts pointProducts = pointProductsService.getOne(new QueryWrapper<PointProducts>().eq(goodsId != null, "goods_id", goodsId));
        return pointProducts;
    }

    @Override
    public void saveExchangePointProductRecords(PointRedemptionHistory pointRedemptionHistory, String token) {
        Employee employee = JSONObject.parseObject(redisTemplateService.getCacheObject(token), Employee.class);
        pointRedemptionHistory.setCn(IdWorker.getIdStr());//生成订单号
        pointRedemptionHistory.setUpdateby(employee.getNickName());
        pointRedemptionHistory.setUpdateId(employee.getId());
        pointRedemptionHistory.setUpdateTime(new Date());
        pointRedemptionHistory.setState(PointRedemptionHistory.STATE_NORMAL);
        /*修改会员的积分*/
        Member member = memberService.getById(pointRedemptionHistory.getMemberId());
        member.setIntegral(member.getIntegral() - pointRedemptionHistory.getIntegral());
        memberService.updateById(member);
        super.save(pointRedemptionHistory);

    }

    @Override
    public List<Map<String, Object>> queryOptionsMemberPhone() {
        QueryWrapper<PointRedemptionHistory> wrapper = new QueryWrapper<PointRedemptionHistory>()
                .select("member_id")
                .eq("state", PointRedemptionHistory.STATE_NORMAL)
                .groupBy("member_id");
        List<PointRedemptionHistory> list = super.list(wrapper);
        List<Long> memberIds = new ArrayList<>();
        for (PointRedemptionHistory pointRedemptionHistory : list) {
            memberIds.add(pointRedemptionHistory.getMemberId());
        }
        if (memberIds == null || memberIds.size() <= 0) {
            return null;
        }
        List<Member> members = memberService.listByIds(memberIds);
        List<Map<String, Object>> vos = new ArrayList<>();
        for (Member member : members) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", member.getId());
            map.put("name", member.getPhone());
            vos.add(map);
        }

        return vos;
    }

    @Override
    public void delExchangePointProducts(String cn) {
        UpdateWrapper<PointRedemptionHistory> wrapper = new UpdateWrapper<PointRedemptionHistory>()
                .set("state", PointRedemptionHistory.STATE_DEL)
                .eq("cn", cn);
        super.update(wrapper);
    }

    @Override
    public Page<PointRedemptionHistory> queryPageByQoExchangePointProducts(QueryExchangePointProductsRecords qo) {
        Page<PointRedemptionHistory> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        QueryWrapper<PointRedemptionHistory> queryWrapper = new QueryWrapper<PointRedemptionHistory>()
                .eq("state", PointRedemptionHistory.STATE_NORMAL)
                .eq(qo.getMemberId() != null, "member_id", qo.getMemberId())
                .ge(StringUtils.hasText(qo.getStartTime()), "update_time", qo.getStartTime())
                .le(StringUtils.hasText(qo.getEndTime()), "update_time", qo.getEndTime())
                .likeRight(StringUtils.hasText(qo.getCn()), "cn", qo.getCn());
        super.page(page, queryWrapper);
        for (PointRedemptionHistory record : page.getRecords()) {
            Member member = memberService.getById(record.getMemberId());
            record.setMemberPhone(member.getPhone());
            Goods goods = goodsService.getById(record.getGoodsId());
            record.setGoodsCoverUrl(goods.getCoverUrl());
            record.setGoodsName(goods.getName());
        }
        return page;
    }

    @Override
    public List<Map<String, Object>> queryOptionsPointProducts() {
        QueryWrapper<PointProducts> wrapper = new QueryWrapper<PointProducts>()
                .eq("state", PointProducts.STATE_NORMAL);
        List<PointProducts> list = pointProductsService.list(wrapper);
        List<Map<String, Object>> vos = new ArrayList<>();
        for (PointProducts pointProducts : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", pointProducts.getGoodsId());
            map.put("name", pointProducts.getGoodsName());
            vos.add(map);
        }
        return vos;
    }

    @Override
    public List<Map<String, Object>> queryOptionsMember() {
        List<Map<String, Object>> vos = new ArrayList<>();
        QueryWrapper<Member> wrapper = new QueryWrapper<Member>()
                .groupBy("id")
                .eq("state", Member.STATE_NORMAL);
        List<Member> list = memberService.list(wrapper);
        for (Member member : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", member.getId());
            map.put("name", member.getPhone());
            vos.add(map);
        }
        return vos;
    }

    @Override
    public List<Map<String, Object>> queryMemberByGoodsId(Long goodsId) {
        List<Member> members = new ArrayList<>();
        QueryWrapper<Member> memberQueryWrapper = new QueryWrapper<Member>();
        memberQueryWrapper.eq("state", Member.STATE_NORMAL);
        if (goodsId != null) {
            PointProducts pointProducts = pointProductsService.getOne(new QueryWrapper<PointProducts>()
                    .eq("goods_id", goodsId)
                    .eq("state", PointProducts.STATE_NORMAL));
            memberQueryWrapper.ge("integral", pointProducts.getIntegral());
        } else {
            memberQueryWrapper.gt("integral", 0);
        }
        members = memberService.list(memberQueryWrapper);
        List<Map<String, Object>> vos = new ArrayList<>();
        for (Member member : members) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", member.getId());
            map.put("name", member.getPhone());
            vos.add(map);
        }
        return vos;
    }
}
