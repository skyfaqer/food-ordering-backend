package com.cgy.sell.aspect;

import com.cgy.sell.constant.CookieConstant;
import com.cgy.sell.constant.RedisConstant;
import com.cgy.sell.enums.ResultEnum;
import com.cgy.sell.exception.SellException;
import com.cgy.sell.exception.SellerAuthorizeException;
import com.cgy.sell.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

// Use AOP to verify seller's login status when accessing seller's management pages
@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut("execution(public * com.cgy.sell.controller.Seller*.*(..)) " +
            "&& !execution(public * com.cgy.sell.controller.SellerUserController.*(..))")
    public void verify() {}

    @Before("verify()")
    public void doVerify() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // Query from cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie == null) {
            log.warn("[Login status verification]: token not found in cookie");
            // throw new SellerAuthorizeException();
        }

        // Query from redis
        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
        if (StringUtils.isEmpty(tokenValue)) {
            log.warn("[Login status verification]: token not found in redis");
            throw new SellerAuthorizeException();
        }
    }
}
