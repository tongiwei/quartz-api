package com.tong.quartz.configuration;

import com.tong.quartz.demo.service.QuartzService;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

@Configuration
public class QuartzConfig implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuartzConfig.class);

    private final QuartzService quartzService;

    public QuartzConfig(QuartzService quartzService) {
        this.quartzService = quartzService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        try {
            quartzService.startJob();
            LOGGER.info("*******quartz调度器启动*******");
        } catch (SchedulerException e) {
            e.printStackTrace();
            LOGGER.info("*******quartz调度器启动异常*******");
        }
    }
}