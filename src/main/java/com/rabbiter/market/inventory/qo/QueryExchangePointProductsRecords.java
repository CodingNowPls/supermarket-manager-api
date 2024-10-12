package com.rabbiter.market.inventory.qo;

import com.rabbiter.market.common.qo.BaseQuery;
import lombok.Data;

/**
 *
 */
@Data
public class QueryExchangePointProductsRecords extends BaseQuery {
    /**
     *
     */
    private String cn;
    /**
     *
     */
    private Long memberId;
    /**
     *
     */
    private String startTime;
    /**
     *
     */
    private String endTime;
}
