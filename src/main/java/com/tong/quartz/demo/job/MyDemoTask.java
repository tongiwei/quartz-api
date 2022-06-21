package com.tong.quartz.demo.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.quartz.JobExecutionContext;

public class MyDemoTask extends QuartzJobBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyDemoTask.class);

    @Override
    protected void executeInternal(JobExecutionContext context) {
        LOGGER.info("Task start");
        //TODO 这里写定时任务的执行逻辑
        LOGGER.info("Task end");
    }
}