package com.rabbiter.market.inventory.qo;

import com.rabbiter.market.common.qo.BaseQuery;
import lombok.Data;

/**
 *
 */
@Data
public class QueryDetailStoreGoods extends BaseQuery {
    /**
     *
     */
    private String cn;
    /**
     *
     */
    private String goodsName;
    /**
     *
     */
    private String state1;
    /**
     *
     */
    private String startCreateTime;
    /**
     *
     */
    private String endCreateTime;

}
