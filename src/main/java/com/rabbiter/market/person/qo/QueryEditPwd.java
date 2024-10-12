package com.rabbiter.market.person.qo;

import lombok.Data;

import java.io.Serializable;

/**
 *
 */
@Data
public class QueryEditPwd implements Serializable {
    /**
     *
     */
    private String username;
    /**
     *
     */
    private String oldPwd;
    /**
     *
     */
    private String newPwd;

}
