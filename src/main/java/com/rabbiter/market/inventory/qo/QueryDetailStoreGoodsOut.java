package com.rabbiter.market.inventory.qo;

import com.rabbiter.market.common.qo.BaseQuery;
import lombok.Data;

/**
 *
 */
@Data
public class QueryDetailStoreGoodsOut extends BaseQuery {
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
    private String state;
    /**
     *
     */
    private String startCreateTime;
    /**
     *
     */
    private String endCreateTime;

}
