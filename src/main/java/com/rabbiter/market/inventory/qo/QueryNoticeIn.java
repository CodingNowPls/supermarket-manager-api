package com.rabbiter.market.inventory.qo;

import com.rabbiter.market.common.qo.BaseQuery;

public class QueryNoticeIn extends BaseQuery {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
