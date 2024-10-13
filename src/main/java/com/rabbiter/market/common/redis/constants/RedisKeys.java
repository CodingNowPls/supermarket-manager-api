package com.rabbiter.market.common.redis.constants;


import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.TimeUnit;

/**
 * @author gao
 */

public enum RedisKeys {
    //用户注册
    LOGIN_USER("LOGIN_USER", 1440L, TimeUnit.MINUTES),
    //登录密码错误次数
    LOGIN_ERRO_PWDNUM("LOGIN_ERRO_PWDNUM", 1L, TimeUnit.DAYS),
    //商品分类信息的缓存
    GOODS_CATEGORY("GOODS_CATEGORY", 24L, TimeUnit.HOURS),
    //账户冻结6小时
    DISABLEUSER("DISABLEUSER", 6L, TimeUnit.HOURS);
    @Getter
    @Setter
    private String prefix;
    /**
     * 失效时间
     */
    @Getter
    @Setter
    private Long expireTime;
    /**
     * 时间单位
     */
    @Getter
    @Setter
    private TimeUnit timeUnit;
    /**
     * key分隔符
     */
    @Getter
    private final String keySeparator = ":";

    RedisKeys(String prefix, Long expireTime, TimeUnit timeUnit) {
        this.prefix = prefix;
        this.expireTime = expireTime;
        this.timeUnit = timeUnit;
    }

    RedisKeys(String prefix, Long timeout) {
        this(prefix, timeout, TimeUnit.MINUTES);
    }

    RedisKeys(String prefix) {
        this(prefix, 30L, TimeUnit.MINUTES);
    }

    public String join(String... suffix) {

        StringBuilder sb = new StringBuilder(80);
        sb.append(this.prefix);
        for (String s : suffix) {
            sb.append(getKeySeparator()).append(s);
        }
        return sb.toString();
    }


}
