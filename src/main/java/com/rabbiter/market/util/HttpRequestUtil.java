package com.rabbiter.market.util;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * HttpServletRequest 工具类
 *
 * @author gao
 */
public class HttpRequestUtil {

    /**
     * 获取当前线程的 HttpServletRequest
     *
     * @return HttpServletRequest 或 null（如果当前没有请求）
     */
    public static HttpServletRequest getCurrentHttpRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            return ((ServletRequestAttributes) requestAttributes).getRequest();
        }
        return null;
    }

    /**
     * 获取当前请求的 token
     *
     * @return
     */
    public static String getToken() {
        HttpServletRequest request = getCurrentHttpRequest();
        if (null == request) {
            throw new RuntimeException("未获取到用户信息");
        }
        return request.getHeader("token");
    }

}
