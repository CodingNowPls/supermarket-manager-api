package com.rabbiter.market.goods.qo;

import com.rabbiter.market.common.qo.BaseQuery;

public class QueryGoodsStore extends BaseQuery {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
