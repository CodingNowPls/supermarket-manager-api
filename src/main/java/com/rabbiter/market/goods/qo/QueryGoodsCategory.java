package com.rabbiter.market.goods.qo;

import com.rabbiter.market.common.qo.BaseQuery;
import lombok.Data;

/**
 *
 */
@Data
public class QueryGoodsCategory extends BaseQuery {
    /**
     *
     */
    private String name;
    /**
     *
     */
    private String state;

}


