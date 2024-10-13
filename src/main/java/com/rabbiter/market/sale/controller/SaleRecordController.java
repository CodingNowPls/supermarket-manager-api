package com.rabbiter.market.sale.controller;

import com.rabbiter.market.common.sercurity.annotation.HasPermission;
import com.rabbiter.market.common.web.response.JsonResult;
import com.rabbiter.market.sale.domain.SaleRecords;
import com.rabbiter.market.sale.qo.QuerySaleRecords;
import com.rabbiter.market.sale.service.ISaleRecordsService;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 销售记录
 */
@RestController
@Validated
@RequestMapping("/sale/saleRecord")
public class SaleRecordController {
    @Autowired
    private ISaleRecordsService saleRecordsService;

    @GetMapping("/getCn")
    public JsonResult getCn() {
        return JsonResult.success(IdWorker.getIdStr());
    }

    @GetMapping("/getOptionSaleRecordsGoods")
    public JsonResult getOptionSaleRecordsGoods() {
        List<Map<String, Object>> list = saleRecordsService.getOptionSaleRecordsGoods();
        return JsonResult.success(list);
    }

    @PostMapping("/saveSaleRecords")
    public JsonResult saveSaleRecords(@RequestBody SaleRecords saleRecords, HttpServletRequest request) {
        saleRecords = saleRecordsService.saveSaleRecords(saleRecords, (String) request.getHeader("token"));
        return JsonResult.success(saleRecords);
    }

    @HasPermission("sale:saleRecordsList")
    @PostMapping("/queryPageByQoSaleRecords")
    public JsonResult queryPageByQoSaleRecords(QuerySaleRecords qo) {
        Page<SaleRecords> page = saleRecordsService.queryPageByQoSaleRecords(qo);
        return JsonResult.success(page);
    }

    @GetMapping("/delSaleRecords")
    public JsonResult delSaleRecords(String cn) {
        saleRecordsService.delSaleRecords(cn);
        return JsonResult.success();
    }
}


