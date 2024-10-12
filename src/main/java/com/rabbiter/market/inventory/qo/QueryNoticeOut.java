package com.rabbiter.market.inventory.qo;

import com.rabbiter.market.common.qo.BaseQuery;
import lombok.Data;

/**
 *
 */
@Data
public class QueryNoticeOut extends BaseQuery {
    /**
     *
     */
    private String name;
    /**
     *
     */
    private String state;

}
