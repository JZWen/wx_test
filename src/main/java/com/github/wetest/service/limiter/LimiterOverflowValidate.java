package com.github.wetest.service.limiter;

/**
 * @author JZWen
 * @date 2020/11/26
 */
public interface LimiterOverflowValidate {

    boolean tryAcquire();
}
