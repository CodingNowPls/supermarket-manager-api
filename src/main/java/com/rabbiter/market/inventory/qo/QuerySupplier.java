package com.rabbiter.market.inventory.qo;

import com.rabbiter.market.common.qo.BaseQuery;
import lombok.Data;

/**
 *
 */
@Data
public class QuerySupplier extends BaseQuery {
    /**
     *
     */
    private String name;
    /**
     *
     */
    private String address;
    /**
     *
     */
    private String info;

}
