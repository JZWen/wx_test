package com.github.wetest.service.task;

import com.github.wetest.service.WxTemplateMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author JZWen
 * @date 2020/11/25
 */
//@Service
public class TaskService {

    private ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    private volatile TaskJob taskJob = null;

    @Autowired
    private WxTemplateMsgService wxTemplateMsgService;

    //@PostConstruct
    public void startup() {
        System.out.println("-----------start up-----------");
        //scheduledExecutorService.scheduleWithFixedDelay(new TaskJob(wxTemplateMsgService), 2, 10, TimeUnit.SECONDS);
        System.out.println("-----------end down------------");
    }

    public TaskJob getJob(WxTemplateMsgService wxTemplateMsgService) {
        if (taskJob != null) {
            return taskJob;
        }
        synchronized (TaskJob.class) {
            if (taskJob == null) {
                taskJob = new TaskJob(wxTemplateMsgService);
            }
        }
        return taskJob;
    }
}
