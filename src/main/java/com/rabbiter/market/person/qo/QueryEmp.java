package com.rabbiter.market.person.qo;

import com.rabbiter.market.common.qo.BaseQuery;
import lombok.Data;

/**
 *
 */
@Data
public class QueryEmp extends BaseQuery {
    /**
     *
     */
    private String username;
    /**
     *
     */
    private String nickName;
    /**
     *
     */
    private String age;
    /**
     *
     */
    private String address;
    /**
     *
     */
    private String sex;
    /**
     *
     */
    private Long deptId;

}
