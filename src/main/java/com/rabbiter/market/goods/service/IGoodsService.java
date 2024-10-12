package com.rabbiter.market.goods.service;

import com.rabbiter.market.goods.doamin.Goods;
import com.rabbiter.market.inventory.domain.DetailStoreGoods;
import com.rabbiter.market.inventory.domain.NoticeIn;
import com.rabbiter.market.inventory.domain.NoticeOut;
import com.rabbiter.market.goods.qo.QueryGoods;
import com.rabbiter.market.goods.qo.QueryGoodsStore;
import com.rabbiter.market.goods.qo.QueryStatisticSale;
import com.rabbiter.market.inventory.qo.QueryNoticeIn;
import com.rabbiter.market.inventory.qo.QueryNoticeOut;
import com.rabbiter.market.goods.vo.NoticeInNotNormalVo;
import com.rabbiter.market.goods.vo.GoodsListVo;
import com.rabbiter.market.goods.vo.GoodsStoreVo;
import com.rabbiter.market.sale.vo.SalesStatisticsVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

public interface IGoodsService extends IService<Goods> {
    /**
     * 分页查询出数据
     *
     * @param qo
     * @return
     */
    Page<GoodsListVo> queryPageByQo(QueryGoods qo);

    void saveGoods(Goods goods, String token);

    void upOrdown(Long gid, String state, String token);

    void updateGoods(Goods goods, String token);

    List<Map<String, Object>> selected_goodsAll();

    List<Map<String, Object>> selected_storeAll();


    void returnGoods(DetailStoreGoods detailStoreGoods, String token);

    Page<GoodsStoreVo> queryPageGoodsStore(QueryGoodsStore qo);

    GoodsStoreVo queryGoodsStoreById(Long id);

    void updateInventory(GoodsStoreVo vo);

    Page<NoticeIn> queryPageNoticeIn(QueryNoticeIn qo);

    Page<NoticeOut> queryPageNoticeOut_shelves(QueryNoticeOut qo);

    void saveOut_shelves(DetailStoreGoods detailStoreGoods, String token);

    SalesStatisticsVo queryPageStatisticSaleByQo(QueryStatisticSale qo);

    Page<NoticeInNotNormalVo> queryPageNoticeOut_untreated(QueryNoticeOut qo);

    void resolveOutUntreatedForm(NoticeInNotNormalVo vo, String token);
}
