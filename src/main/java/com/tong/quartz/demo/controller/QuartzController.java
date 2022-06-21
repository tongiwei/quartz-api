package com.tong.quartz.demo.controller;

import com.tong.quartz.demo.entity.QuartzBean;
import com.tong.quartz.demo.service.QuartzService;
import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quartz/")
public class QuartzController {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuartzService.class);

    private final Scheduler scheduler;
    private final QuartzService quartzService;

    public QuartzController(Scheduler scheduler, QuartzService quartzService) {
        this.scheduler = scheduler;
        this.quartzService = quartzService;
    }

    @PostMapping("/createJob")
    @ResponseBody
    public String createJob(@RequestBody QuartzBean quartzBean) {
        try {
            //进行测试所以写死
            quartzBean.setJobClass("com.tong.quartz.demo.job.MyDemoTask");
            quartzBean.setJobName("test1");
            quartzBean.setCronExpression("*/10 * * * * ?");
            quartzService.createScheduleJob(scheduler, quartzBean);
        } catch (Exception e) {
            LOGGER.error("创建失败");
            return "创建失败";
        }
        LOGGER.info("创建成功");
        return "创建成功";
    }

    @GetMapping("/pauseJob")
    @ResponseBody
    public String pauseJob() {
        try {
            quartzService.pauseScheduleJob(scheduler, "test1");
        } catch (Exception e) {
            LOGGER.error("暂停失败");
            return "暂停失败";
        }
        LOGGER.info("暂停成功");
        return "暂停成功";
    }

    @GetMapping("/runOnce")
    @ResponseBody
    public String runOnce(String jobName) {
        try {
            quartzService.runOnce(scheduler, jobName);
        } catch (Exception e) {
            return "运行一次失败";
        }
        return "运行一次成功";
    }

    @GetMapping("/resume")
    @ResponseBody
    public String resume() {
        try {
            quartzService.resumeScheduleJob(scheduler, "test1");
        } catch (Exception e) {
            return "启动失败";
        }
        return "启动成功";
    }

    @PutMapping("/update")
    @ResponseBody
    public String update(@RequestBody QuartzBean quartzBean) {
        try {
            //进行测试所以写死
            quartzBean.setJobClass("com.tong.quartz.demo.job.MyDemoTask");
            quartzBean.setJobName("test1");
            quartzBean.setCronExpression("10 * * * * ?");
            quartzService.updateScheduleJob(scheduler, quartzBean);
        } catch (Exception e) {
            return "启动失败";
        }
        return "启动成功";
    }
}
