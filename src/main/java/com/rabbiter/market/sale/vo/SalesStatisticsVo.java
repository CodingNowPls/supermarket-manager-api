package com.rabbiter.market.sale.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.io.Serializable;

/**
 * 商品销售量统计
 */
@Data
public class SalesStatisticsVo implements Serializable {
    /**
     *
     */
    private Long total; //所有商品总售卖量
    /**
     *
     */
    private Page<SaleGoodsVo> vos;

}
