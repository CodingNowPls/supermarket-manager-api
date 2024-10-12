package com.rabbiter.market.common.qo;


import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 *
 */
@Data
public class BaseQuery implements Serializable {
    private Integer currentPage = 1;
    private Integer pageSize = 3;
    //其他请求参数
    private Map<String, Object> params;

}
