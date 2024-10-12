package com.rabbiter.market.inventory.qo;

import lombok.Data;
import java.io.Serializable;

/**
 *
 */
@Data
public class QueryStore implements Serializable {
    /**
     *
     */
    private String name;
    /**
     *
     */
    private String state;

}
