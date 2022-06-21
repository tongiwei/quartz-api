package com.tong.quartz.demo.service;

import com.tong.quartz.demo.entity.QuartzBean;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class QuartzService {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuartzService.class);


    public void startJob() throws SchedulerException {
        // TODO 这里可以放一些初始化的任务，应用启动开始执行
    }

    /**
     * 创建定时任务 定时任务创建之后默认启动状态
     */
    @SuppressWarnings("unchecked")
    public void createScheduleJob(Scheduler scheduler, QuartzBean quartzBean) {
        try {
            //获取到定时任务的执行类  必须是类的绝对路径名称
            //定时任务类需要是job类的具体实现 QuartzJobBean是job的抽象类。
            Class<? extends Job> jobClass = (Class<? extends Job>) Class.forName(quartzBean.getJobClass());
            // 构建定时任务信息
            JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(quartzBean.getJobName()).build();
            // 设置定时任务执行方式
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(quartzBean.getCronExpression());
            // 构建触发器trigger
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(quartzBean.getJobName()).withSchedule(scheduleBuilder).build();
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (ClassNotFoundException e) {
            LOGGER.error("定时任务类路径出错：请输入类的绝对路径");
        } catch (SchedulerException e) {
            LOGGER.error("创建定时任务出错：" + e.getMessage());
        }
    }

    /**
     * 根据任务名称暂停定时任务
     */
    public void pauseScheduleJob(Scheduler scheduler, String jobName) {
        JobKey jobKey = JobKey.jobKey(jobName);
        try {
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            LOGGER.error("暂停定时任务出错：" + e.getMessage());
        }
    }

    /**
     * 根据任务名称恢复定时任务
     */
    public void resumeScheduleJob(Scheduler scheduler, String jobName) {
        JobKey jobKey = JobKey.jobKey(jobName);
        try {
            scheduler.resumeJob(jobKey);
        } catch (SchedulerException e) {
            LOGGER.error("启动定时任务出错：" + e.getMessage());
        }
    }

    /**
     * 根据任务名称立即运行一次定时任务
     */
    public void runOnce(Scheduler scheduler, String jobName) {
        JobKey jobKey = JobKey.jobKey(jobName);
        try {
            scheduler.triggerJob(jobKey);
        } catch (SchedulerException e) {
            LOGGER.error("运行定时任务出错：" + e.getMessage());
        }
    }

    /**
     * 更新定时任务
     */
    public void updateScheduleJob(Scheduler scheduler, QuartzBean quartzBean) {
        try {
            //获取到对应任务的触发器
//            var triggerKey = TriggerKey.triggerKey(quartzBean.getJobName());
            TriggerKey triggerKey = TriggerKey.triggerKey(quartzBean.getJobName());
            //设置定时任务执行方式
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(quartzBean.getCronExpression());
            //重新构建任务的触发器trigger
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            //重置对应的job
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            LOGGER.error("更新定时任务出错：" + e.getMessage());
        }
    }

    /**
     * 根据定时任务名称从调度器当中删除定时任务
     */
    public void deleteScheduleJob(Scheduler scheduler, String jobName) {
        JobKey jobKey = JobKey.jobKey(jobName);
        try {
            scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            LOGGER.error("删除定时任务出错：" + e.getMessage());
        }
    }
}
