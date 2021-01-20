package com.xugw.kettlequartz.service;

import com.xugw.kettlequartz.domain.ScheduleJobEntity;
import org.quartz.SchedulerException;


import java.util.Date;

/**
 * @Title: quartz任务执行类
 * @Description:
 * @Author: xugw
 * @Date: 2021/1/14 - 13:06
 */
public interface QuartzService {

    /**
     * 立即执行
     * @param scheduleJobEntity
     */
    void execNow(ScheduleJobEntity scheduleJobEntity) throws SchedulerException;

    /**
     * 添加任务
     * @param scheduleJobEntity
     */
    void addJob(ScheduleJobEntity scheduleJobEntity) throws SchedulerException;

    void updateJob(ScheduleJobEntity scheduleJobEntity) throws SchedulerException;

    void deleteJob(ScheduleJobEntity scheduleJobEntity) throws SchedulerException;

    /**
     * 暂停任务调度
     * @param scheduleJobEntity
     */
    void pauseJob(ScheduleJobEntity scheduleJobEntity) throws SchedulerException;

    /**
     * 拉起任务调度
     * @param scheduleJobEntity
     */
    void resumeJob(ScheduleJobEntity scheduleJobEntity) throws SchedulerException;

    /**
     * 获取任务状态
     * @param scheduleJobEntity
     * @return
     */
    String getTriggerState(ScheduleJobEntity scheduleJobEntity) throws SchedulerException;

    /**
     * 获取下次运行时间
     * @param scheduleJobEntity
     * @return
     */
    Date getFireTimeAfter(ScheduleJobEntity scheduleJobEntity) throws SchedulerException;

    String getJobName(ScheduleJobEntity scheduleJob);

}
