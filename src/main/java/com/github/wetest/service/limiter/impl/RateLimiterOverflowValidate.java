package com.github.wetest.service.limiter.impl;

import com.github.wetest.service.limiter.LimiterOverflowValidate;
import org.springframework.stereotype.Service;

/**
 * @author JZWen
 * @date 2020/11/26
 */
@Service
public class RateLimiterOverflowValidate implements LimiterOverflowValidate {
    private GlobalLimiter limiter = new GlobalLimiter();

    @Override
    public boolean tryAcquire() {
        return limiter.limit();
    }

}
