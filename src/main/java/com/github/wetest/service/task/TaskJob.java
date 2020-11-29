package com.github.wetest.service.task;

import com.github.wetest.domain.info.WxTemplateMsgInfo;
import com.github.wetest.service.WxTemplateMsgService;

/**
 * @author JZWen
 * @date 2020/11/25
 */
public class TaskJob implements Runnable{

    private WxTemplateMsgService wxTemplateMsgService;

    public TaskJob(WxTemplateMsgService wxTemplateMsgService) {
        this.wxTemplateMsgService = wxTemplateMsgService;
    }

    @Override
    public void run() {
//        System.out.println("开始执行任务-------------------");
//        try {
//            wxTemplateMsgService.send(new WxTemplateMsgInfo());
//            Thread.sleep(1000L);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println("执行任务结束-------------------");
    }
}
