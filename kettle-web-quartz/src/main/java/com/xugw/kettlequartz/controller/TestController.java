package com.xugw.kettlequartz.controller;


import com.xugw.kettlequartz.domain.ScheduleJobEntity;
import com.xugw.kettlequartz.service.QuartzService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


/**
 * @Title: 测试数据库连接
 * @Description:
 * @Author: xugw
 * @Date: 2021/1/12 - 14:39
 */
@RestController
public class TestController {
    @Autowired
    QuartzService quartzService;

    @RequestMapping("/execNow")
    public String execNow() throws SchedulerException {
        ScheduleJobEntity scheduleJob = new ScheduleJobEntity();
        scheduleJob.setJobId(1L);
        scheduleJob.setBeanName("defaultJob");
        quartzService.execNow(scheduleJob);
        return "success";
    }

    @RequestMapping("/addjob")
    public String addjob() throws SchedulerException {
        ScheduleJobEntity scheduleJob = new ScheduleJobEntity();
        scheduleJob.setJobId(1L);
        scheduleJob.setBeanName("defaultJob");
        scheduleJob.setCron("0 0/2 * * * ?");
        quartzService.addJob(scheduleJob);
        return "success";
    }

    @RequestMapping("/hello")
    public String hello() throws SchedulerException {
        return "success";
    }
}
