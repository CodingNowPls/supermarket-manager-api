package com.rabbiter.market.inventory.controller;

import com.rabbiter.market.common.sercurity.annotation.HasPermission;
import com.rabbiter.market.common.web.response.JsonResult;
import com.rabbiter.market.inventory.domain.GoodsStockDetail;
import com.rabbiter.market.inventory.domain.NoticeIn;
import com.rabbiter.market.inventory.domain.NoticeOut;
import com.rabbiter.market.inventory.qo.QueryNoticeIn;
import com.rabbiter.market.inventory.qo.QueryNoticeOut;
import com.rabbiter.market.goods.service.IGoodsService;
import com.rabbiter.market.goods.vo.NoticeInNotNormalVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author gao
 */
@RestController
@Validated
@RequestMapping("/inventory/stockDetail/notice")
public class StockDetailNoticeController {
    @Autowired
    private IGoodsService goodsService;

    @HasPermission("inventory:stockDetailIn:notice:list")
    @PostMapping("/queryPageNoticeIn")
    public JsonResult queryPageNoticeIn(@RequestBody QueryNoticeIn qo) {
        Page<NoticeIn> page = goodsService.queryPageNoticeIn(qo);
        return JsonResult.success(page);
    }

    @HasPermission("inventory:stockDetailOut:notice:list")
    @PostMapping("/queryPageNoticeOut_shelves")
    public JsonResult queryPageNoticeOut_shelves(@RequestBody QueryNoticeOut qo) {
        Page<NoticeOut> page = goodsService.queryPageNoticeOut_shelves(qo);
        return JsonResult.success(page);
    }

    @HasPermission("inventory:stockDetailOut:notice:saveOut_shelves")
    @PostMapping("/saveOut_shelves")
    public JsonResult saveOut_shelves(@RequestBody GoodsStockDetail goodsStockDetail) {
        goodsService.saveOut_shelves(goodsStockDetail);
        return JsonResult.success();
    }

    @HasPermission("inventory:stockDetailOut:notice:list")
    @PostMapping("/queryPageNoticeOut_untreated")
    public JsonResult queryPageNoticeOut_untreated(@RequestBody QueryNoticeOut qo) {
        Page<NoticeInNotNormalVo> page = goodsService.queryPageNoticeOut_untreated(qo);
        return JsonResult.success(page);
    }

    @HasPermission("inventory:stockDetailOut:notice:resolveOutUntreatedForm")
    @PostMapping("/resolveOutUntreatedForm")
    public JsonResult resolveOutUntreatedForm(@RequestBody NoticeInNotNormalVo vo) {
        goodsService.resolveOutUntreatedForm(vo);
        return JsonResult.success();
    }


}
