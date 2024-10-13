package com.rabbiter.market.goods.mapper;

import com.rabbiter.market.goods.doamin.Goods;
import com.rabbiter.market.inventory.domain.NoticeIn;
import com.rabbiter.market.inventory.domain.NoticeOut;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author gao
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {

    int getNoticeInTotalCount(Map<String, Object> map);

    List<NoticeIn> getNoticePageList(Map<String, Object> map);

    int getNoticeOutShelvesTotalCount(Map<String, Object> map);

    List<NoticeOut> getNoticeShelvesPageList(Map<String, Object> map);

    Long queryPageStatisticSaleByQo(String name);
}
