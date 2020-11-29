package com.github.wetest.service.limiter.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author JZWen
 * @date 2020/11/26
 */
public class GlobalLimiter {

    //随着线上的机子量变化需要修改限流数，目前是一秒十个请求
    private static volatile int permit = 3;

    private Map<Long, Integer> rate = new ConcurrentHashMap<>(500);

    public boolean limit() {
        long secondTime = System.currentTimeMillis() / 10000;
        synchronized (GlobalLimiter.class) {
            Integer count = rate.get(secondTime);
            if (count == null) {
                rate.put(secondTime, 1);
                System.out.println("开始-------count ：" + count + 1);
                return true;
            }
            if (count + 1 > permit) {
                System.out.println("限流了 -------" + count + 1);
                return false;
            }
            rate.put(secondTime, count + 1);
            System.out.println("通过了-------" + count + 1);
            return true;
        }
    }
}
