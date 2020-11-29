package com.github.wetest.service;

import com.github.wetest.domain.info.WxTemplateMsgInfo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author JZWen
 * @date 2020/10/27
 */
public interface WxTemplateMsgService {
    ExecutorService executors = new ThreadPoolExecutor(20, 50, 1L, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>(10000));

    String send(WxTemplateMsgInfo wxTemplateMsgInfo) throws Exception;
}
