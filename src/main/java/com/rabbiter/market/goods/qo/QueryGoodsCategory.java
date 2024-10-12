package com.rabbiter.market.goods.qo;

import com.rabbiter.market.common.qo.BaseQuery;

public class QueryGoodsCategory extends BaseQuery {
    private String name;
    private String state;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}


