package com.rabbiter.market.member.qo;

import com.rabbiter.market.common.qo.BaseQuery;
import lombok.Data;

/**
 *
 */
@Data
public class QueryMember extends BaseQuery {
    /**
     *
     */
    private String phone;
    /**
     *
     */
    private String state;
    /**
     *
     */
    private String name;

}
