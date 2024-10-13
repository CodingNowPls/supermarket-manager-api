package com.rabbiter.market.sale.service.impl;

import com.rabbiter.market.common.redis.service.RedisTemplateService;
import com.rabbiter.market.goods.doamin.Goods;
import com.rabbiter.market.goods.doamin.PointGoods;
import com.rabbiter.market.member.domain.Member;
import com.rabbiter.market.person.domain.Employee;
import com.rabbiter.market.sale.domain.PointRedemption;
import com.rabbiter.market.sale.mapper.PointRedemptionHistoryMapper;
import com.rabbiter.market.inventory.qo.QueryExchangePointProductsRecords;
import com.rabbiter.market.goods.service.IGoodsService;
import com.rabbiter.market.goods.service.IPointGoodsService;
import com.rabbiter.market.member.service.IMemberService;
import com.rabbiter.market.sale.service.IPointRedemptionService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rabbiter.market.util.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class PointRedemptionServiceImpl extends ServiceImpl<PointRedemptionHistoryMapper, PointRedemption> implements IPointRedemptionService {
    @Autowired
    private IMemberService memberService;
    @Autowired
    private IPointGoodsService pointProductsService;
    @Autowired
    private RedisTemplateService redisTemplateService;
    @Autowired
    private IGoodsService goodsService;

    @Override
    public List<Map<String, Object>> queryPointProductBymemberId(Long memberId) {
        Member member = memberService.getById(memberId);

        QueryWrapper<PointGoods> pointProductsQueryWrapper = new QueryWrapper<PointGoods>();
        if (memberId == null) {
            pointProductsQueryWrapper.gt("integral", 0L);
        } else {
            pointProductsQueryWrapper.le("integral", member.getIntegral());
        }

        List<PointGoods> list = pointProductsService.list(pointProductsQueryWrapper);
        List<Map<String, Object>> mapArrayList = new ArrayList<>();
        for (PointGoods pointGoods : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", pointGoods.getGoodsId());
            map.put("name", pointGoods.getGoodsName());
            mapArrayList.add(map);
        }
        return mapArrayList;
    }

    @Override
    public PointGoods queryPointProductByGoodsId(Long goodsId) {
        PointGoods pointGoods = pointProductsService.getOne(new QueryWrapper<PointGoods>().eq(goodsId != null, "goods_id", goodsId));
        return pointGoods;
    }

    @Override
    public void saveExchangePointProductRecords(PointRedemption pointRedemption) {
        String token = HttpRequestUtil.getToken();
        Employee employee = JSONObject.parseObject(redisTemplateService.getCacheObject(token), Employee.class);
        pointRedemption.setCn(IdWorker.getIdStr());//生成订单号
        pointRedemption.setUpdateby(employee.getNickName());
        pointRedemption.setUpdateId(employee.getId());
        pointRedemption.setUpdateTime(new Date());
        pointRedemption.setState(PointRedemption.STATE_NORMAL);
        /*修改会员的积分*/
        Member member = memberService.getById(pointRedemption.getMemberId());
        member.setIntegral(member.getIntegral() - pointRedemption.getIntegral());
        memberService.updateById(member);
        super.save(pointRedemption);

    }

    @Override
    public List<Map<String, Object>> queryOptionsMemberPhone() {
        QueryWrapper<PointRedemption> wrapper = new QueryWrapper<PointRedemption>()
                .select("member_id")
                .eq("state", PointRedemption.STATE_NORMAL)
                .groupBy("member_id");
        List<PointRedemption> list = super.list(wrapper);
        List<Long> memberIds = new ArrayList<>();
        for (PointRedemption pointRedemption : list) {
            memberIds.add(pointRedemption.getMemberId());
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
        UpdateWrapper<PointRedemption> wrapper = new UpdateWrapper<PointRedemption>()
                .set("state", PointRedemption.STATE_DEL)
                .eq("cn", cn);
        super.update(wrapper);
    }

    @Override
    public Page<PointRedemption> queryPageByQoExchangePointProducts(QueryExchangePointProductsRecords qo) {
        Page<PointRedemption> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        QueryWrapper<PointRedemption> queryWrapper = new QueryWrapper<PointRedemption>()
                .eq("state", PointRedemption.STATE_NORMAL)
                .eq(qo.getMemberId() != null, "member_id", qo.getMemberId())
                .ge(StringUtils.hasText(qo.getStartTime()), "update_time", qo.getStartTime())
                .le(StringUtils.hasText(qo.getEndTime()), "update_time", qo.getEndTime())
                .likeRight(StringUtils.hasText(qo.getCn()), "cn", qo.getCn());
        super.page(page, queryWrapper);
        for (PointRedemption record : page.getRecords()) {
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
        QueryWrapper<PointGoods> wrapper = new QueryWrapper<PointGoods>()
                .eq("state", PointGoods.STATE_NORMAL);
        List<PointGoods> list = pointProductsService.list(wrapper);
        List<Map<String, Object>> vos = new ArrayList<>();
        for (PointGoods pointGoods : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", pointGoods.getGoodsId());
            map.put("name", pointGoods.getGoodsName());
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
            PointGoods pointGoods = pointProductsService.getOne(new QueryWrapper<PointGoods>()
                    .eq("goods_id", goodsId)
                    .eq("state", PointGoods.STATE_NORMAL));
            memberQueryWrapper.ge("integral", pointGoods.getIntegral());
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
