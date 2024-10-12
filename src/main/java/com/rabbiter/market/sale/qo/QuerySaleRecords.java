package com.rabbiter.market.sale.qo;

import com.rabbiter.market.common.qo.BaseQuery;
import lombok.Data;

@Data
public class QuerySaleRecords extends BaseQuery {

    private String cn;

    private String startSellTime;

    private String endSellTime;

    private String type;

    private String sellway;

}
