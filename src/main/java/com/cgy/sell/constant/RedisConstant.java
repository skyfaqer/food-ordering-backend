package com.cgy.sell.constant;

public interface RedisConstant {

    // Prefix of token key
    String TOKEN_PREFIX = "token_%s";

    // Expiration time for token: 2 hours
    Integer EXPIRE = 7200;
}
