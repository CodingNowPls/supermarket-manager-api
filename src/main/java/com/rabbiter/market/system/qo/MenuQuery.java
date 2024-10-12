package com.rabbiter.market.system.qo;

import com.rabbiter.market.common.qo.BaseQuery;

public class MenuQuery extends BaseQuery {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
