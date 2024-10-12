package com.rabbiter.market.goods.qo;

import com.rabbiter.market.common.qo.BaseQuery;
import lombok.Data;

/**
 *
 */
@Data
public class QueryGoods extends BaseQuery {
    /**
     *
     */
    private Long id;
    /**
     *
     */
    private String name;
    /**
     *
     */
    private Double sellPrice;
    /**
     *
     */
    private Long categoryId;
    /**
     *
     */
    private String state;
    /**
     *
     */
    private String operateStartTime;
    /**
     *
     */
    private String operateEndTime;

}
