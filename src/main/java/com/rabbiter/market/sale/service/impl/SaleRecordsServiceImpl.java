package com.rabbiter.market.sale.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.rabbiter.market.common.redis.service.RedisTemplateService;
import com.rabbiter.market.goods.doamin.Goods;
import com.rabbiter.market.member.domain.Member;
import com.rabbiter.market.person.domain.Employee;
import com.rabbiter.market.sale.domain.SaleRecordDetail;
import com.rabbiter.market.sale.domain.SaleRecord;
import com.rabbiter.market.sale.mapper.SaleRecordsMapper;
import com.rabbiter.market.sale.qo.QuerySaleRecords;
import com.rabbiter.market.goods.service.IGoodsService;
import com.rabbiter.market.inventory.service.IGoodsStockService;
import com.rabbiter.market.member.service.IMemberService;
import com.rabbiter.market.sale.service.IDetailSaleRecordsService;
import com.rabbiter.market.sale.service.ISaleRecordsService;
import com.alibaba.fastjson.JSON;
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
public class SaleRecordsServiceImpl extends ServiceImpl<SaleRecordsMapper, SaleRecord> implements ISaleRecordsService {
    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private RedisTemplateService redisTemplateService;
    @Autowired
    private IDetailSaleRecordsService detailSaleRecordsService;
    @Autowired
    private IGoodsStockService goodsStoreService;
    @Autowired
    private IMemberService memberService;

    @Override
    public List<Map<String, Object>> getOptionSaleRecordsGoods() {
        LambdaQueryWrapper<Goods> wrapper = Wrappers.lambdaQuery(Goods.class)
                .gt(Goods::getResidueNum, 0L);
        List<Goods> list = goodsService.list(wrapper);
        List<Map<String, Object>> goodsList = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (Goods goods : list) {
                Map<String, Object> map = new HashMap<>();
                map.put("id", goods.getId());
                map.put("name", goods.getName());
                map.put("residueNum", goods.getResidueNum());
                goodsList.add(map);
            }
        }
        return goodsList;
    }

    @Override
    public SaleRecord saveSaleRecords(SaleRecord saleRecord) {
        String token = HttpRequestUtil.getToken();
        Employee employee = JSON.parseObject(redisTemplateService.getCacheObject(token), Employee.class);
        saleRecord.setEid(employee.getId());
        saleRecord.setSellTime(new Date());
        saleRecord.setSellby(employee.getNickName());
        saleRecord.setState(SaleRecord.STATE_NORMAL);
        for (SaleRecordDetail detailSaleRecord : saleRecord.getSaleRecords()) {
            detailSaleRecord.setSellCn(saleRecord.getCn());
            Goods goods = goodsService.getById(detailSaleRecord.getGoodsId());
            LambdaUpdateWrapper<Goods> wrapper = Wrappers.lambdaUpdate(Goods.class)
                    .set(Goods::getSalesVolume,
                            goods.getSalesVolume() != null ? goods.getSalesVolume() + detailSaleRecord.getGoodsNum() : detailSaleRecord.getGoodsNum())
                    .set(Goods::getResidueNum, goods.getResidueNum() - detailSaleRecord.getGoodsNum())
                    .eq(Goods::getId, goods.getId());
            goodsService.update(wrapper);
        }
        detailSaleRecordsService.saveBatch(saleRecord.getSaleRecords());
        super.save(saleRecord);
        if ("1".equals(saleRecord.getType())) {
            //为会员增加积分 积分规则：积分=总金额*5%取整
            String s = saleRecord.getSellTotalmoney() * 0.05 + "";
            long integral = Long.parseLong(s.split("\\.")[0]);
            LambdaQueryWrapper<Member> memberQueryWrapper = Wrappers.lambdaQuery(Member.class)
                    .eq(Member::getPhone, saleRecord.getMemberPhone());
            Member member = memberService.getOne(memberQueryWrapper);
            LambdaUpdateWrapper<Member> memberUpdateWrapper = Wrappers.lambdaUpdate(Member.class)
                    .set(Member::getIntegral,
                            member.getIntegral() != null ? member.getIntegral() + integral : integral)
                    .eq(Member::getPhone, saleRecord.getMemberPhone());
            memberService.update(memberUpdateWrapper);
        }
        return saleRecord;
    }

    @Override
    public Page<SaleRecord> queryPageByQoSaleRecords(QuerySaleRecords qo) {
        Page<SaleRecord> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        LambdaQueryWrapper<SaleRecord> queryWrapper = Wrappers.lambdaQuery(SaleRecord.class)
                .eq(SaleRecord::getState, SaleRecord.STATE_NORMAL)
                .eq(StringUtils.hasText(qo.getType()), SaleRecord::getType, qo.getType())
                .likeRight(StringUtils.hasText(qo.getCn()), SaleRecord::getCn, qo.getCn())
                .ge(StringUtils.hasText(qo.getStartSellTime()), SaleRecord::getSellTime, qo.getStartSellTime())
                .le(StringUtils.hasText(qo.getEndSellTime()), SaleRecord::getSellTime, qo.getEndSellTime())
                .eq(StringUtils.hasText(qo.getSellway()), SaleRecord::getSellway, qo.getSellway());
        super.page(page, queryWrapper);

        List<SaleRecord> records = page.getRecords();
        if (records != null && records.size() > 0) {
            for (SaleRecord record : records) {
                LambdaQueryWrapper<SaleRecordDetail> sellCnWrapper = Wrappers.lambdaQuery(SaleRecordDetail.class)
                        .eq(SaleRecordDetail::getSellCn, record.getCn());
                List<SaleRecordDetail> list = detailSaleRecordsService.list(sellCnWrapper);

                record.setSaleRecords(list);
            }
        }
        return page;
    }

    @Override
    public void delSaleRecords(String cn) {
        LambdaUpdateWrapper<SaleRecord> wrapper = Wrappers.lambdaUpdate(SaleRecord.class)
                .eq(SaleRecord::getCn, cn)
                .set(SaleRecord::getState, SaleRecord.STATE_DEL);
        super.update(wrapper);

    }

}
