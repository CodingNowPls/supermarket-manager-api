package com.rabbiter.market.goods.controller;

import com.rabbiter.market.common.sercurity.annotation.NoRequireLogin;
import com.rabbiter.market.common.util.PathUtils;
import com.rabbiter.market.common.web.response.JsonResult;
import com.rabbiter.market.goods.doamin.Goods;
import com.rabbiter.market.inventory.domain.GoodsStockDetail;
import com.rabbiter.market.goods.qo.QueryGoods;
import com.rabbiter.market.goods.service.IGoodsService;
import com.rabbiter.market.goods.vo.GoodsListVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品列表
 */
@RestController
@Validated
@RequestMapping("/goods/goods")
public class GoodsController {
    @Autowired
    private IGoodsService goodsService;

    /*查询信息*/
    @PostMapping("/queryPageByQo")
    public JsonResult queryPageByQo(@RequestBody QueryGoods qo) {
        Page<GoodsListVo> page = goodsService.queryPageByQo(qo);
        return JsonResult.success(page);
    }

    /**
     * 上传图片到阿里云oss
     * 返回网络图片地址,uploaded:1:成功 0:失败
     *
     * @param upload
     * @return
     */
    @NoRequireLogin
    @PostMapping("/uploadImg")
    public Map<String, Object> uploadImg(@RequestParam("file") MultipartFile upload) {
        Map<String, Object> map = new HashMap<>();
        if (upload != null && upload.getSize() > 0) {
            String path = "";
            try {
                path = PathUtils.upload(upload);
                map.put("uploaded", 1);  //成功
                map.put("url", path);  //成功
            } catch (Exception e) {
                e.printStackTrace();
                map.put("uploaded", 0);  //失败
                Map<String, Object> errorMap = new HashMap<>();
                errorMap.put("message", e.getMessage());
                map.put("error", errorMap);
            }
        } else {
            map.put("uploaded", 0);  //失败
            Map<String, Object> errorMap = new HashMap<>();
            errorMap.put("message", "上传失败，图片文件异常");
            map.put("error", errorMap);
        }
        return map;
    }

    /*保存*/
    @PostMapping("/save")
    public JsonResult saveGoods(@RequestBody Goods goods) {
        goodsService.saveGoods(goods);
        return JsonResult.success();
    }
    /*上/下架*/

    @PostMapping("/upOrdown")
    public JsonResult upOrdown(@NotNull(message = "商品编号不能为空") Long gid, String state) {
        goodsService.upOrdown(gid, state);
        return JsonResult.success();
    }

    @GetMapping("/queryGoodsById")
    public JsonResult queryGoodsById(@NotNull(message = "商品编号不能为空") Long id) {
        return JsonResult.success(goodsService.getById(id));
    }

    @PostMapping("/update")
    public JsonResult update(@RequestBody Goods goods) {
        goodsService.updateGoods(goods);
        return JsonResult.success();
    }

    @GetMapping("/selected_goodsAll")
    public JsonResult selected_goodsAll() {
        List<Map<String, Object>> list = goodsService.selected_goodsAll();
        return JsonResult.success(list);
    }

    @GetMapping("/selected_storeAll")
    public JsonResult selected_storeAll() {
        List<Map<String, Object>> list = goodsService.selected_storeAll();
        return JsonResult.success(list);
    }


    /*退还商品入库*/
    @PostMapping("/returnGoods")
    public JsonResult returnGoods(@RequestBody GoodsStockDetail goodsStockDetail) {
        goodsService.returnGoods(goodsStockDetail);
        return JsonResult.success();
    }


}
