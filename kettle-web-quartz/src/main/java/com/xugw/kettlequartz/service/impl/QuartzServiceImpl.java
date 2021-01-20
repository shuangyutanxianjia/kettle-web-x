package com.xugw.kettlequartz.service.impl;

import com.xugw.kettlequartz.domain.ScheduleJobEntity;
import com.xugw.kettlequartz.exec.QuartzJobFactory;
import com.xugw.kettlequartz.service.QuartzService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Title: quartz任务执行实现类
 * @Description:
 * @Author: xugw
 * @Date: 2021/1/14 - 13:13
 */

@Service
public class QuartzServiceImpl implements QuartzService {

    @Autowired
    Scheduler scheduler;

    public void execNow(ScheduleJobEntity scheduleJob) throws SchedulerException {
        String jobName = getJobName(scheduleJob);
        JobDetail jobDetail = JobBuilder.newJob(QuartzJobFactory.class)
                .withIdentity(jobName)
                .build();
        jobDetail.getJobDataMap().put("scheduleJob", scheduleJob);

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("jobtest")
                .startNow().build();
        scheduler.scheduleJob(jobDetail, trigger);

    }

    public void addJob(ScheduleJobEntity scheduleJob) throws SchedulerException {
        String jobName = getJobName(scheduleJob);
        JobDetail jobDetail = JobBuilder.newJob(QuartzJobFactory.class)
                .withIdentity(jobName)
                .build();
        jobDetail.getJobDataMap().put("scheduleJob", scheduleJob);
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCron());
        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .withIdentity(jobName)
                .withSchedule(cronScheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, cronTrigger);
    }

    public void updateJob(ScheduleJobEntity scheduleJob) throws SchedulerException {
        String jobName = getJobName(scheduleJob);
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName);
        CronTrigger oldTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCron());
        CronTrigger newTrigger = oldTrigger.getTriggerBuilder()
                .withIdentity(triggerKey)
                .withSchedule(cronScheduleBuilder)
                .build();
        JobKey jobKey = JobKey.jobKey(jobName);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        jobDetail.getJobDataMap().put("scheduleJob", scheduleJob);
        scheduler.rescheduleJob(triggerKey, newTrigger);
    }

    public void deleteJob(ScheduleJobEntity scheduleJob) throws SchedulerException {
        String jobName = getJobName(scheduleJob);
        JobKey jobKey = JobKey.jobKey(jobName);
        scheduler.deleteJob(jobKey);
    }

    public void pauseJob(ScheduleJobEntity scheduleJob) throws SchedulerException {
        String jobName = getJobName(scheduleJob);
        JobKey jobKey = JobKey.jobKey(jobName);
        scheduler.pauseJob(jobKey);
    }

    public void resumeJob(ScheduleJobEntity scheduleJob) throws SchedulerException {
        String jobName = getJobName(scheduleJob);
        JobKey jobKey = JobKey.jobKey(jobName);
        scheduler.resumeJob(jobKey);
    }

    public String getTriggerState(ScheduleJobEntity scheduleJob) throws SchedulerException {
        String jobName = getJobName(scheduleJob);
        JobKey jobKey = JobKey.jobKey(jobName);
        TriggerKey triggerKey = TriggerKey.triggerKey(jobKey.getName(), jobKey.getGroup());
        return scheduler.getTriggerState(triggerKey).toString();
    }

    public Date getFireTimeAfter(ScheduleJobEntity scheduleJob) throws SchedulerException {
        String jobName = getJobName(scheduleJob);
        JobKey jobKey = JobKey.jobKey(jobName);
        TriggerKey triggerKey = TriggerKey.triggerKey(jobKey.getName(), jobKey.getGroup());
        Date date = scheduler.getTriggersOfJob(jobKey).get(0).getNextFireTime();
        return date;
    }

    public String getJobName(ScheduleJobEntity scheduleJob) {
        return "job_" + scheduleJob.getJobId() + "_" + scheduleJob.getId();
    }
}
