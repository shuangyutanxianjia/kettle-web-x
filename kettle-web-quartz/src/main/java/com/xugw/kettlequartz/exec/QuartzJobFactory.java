package com.xugw.kettlequartz.exec;

import com.xugw.kettlequartz.domain.ScheduleJobEntity;
import com.xugw.kettlequartz.job.AbstractJob;
import com.xugw.kettlequartz.rpc.KettleRpc;
import com.xugw.kettlequartz.util.ApplicationContextUtil;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @Title: quartz工厂
 * @Description: 每生成一个job 实例一个对象
 * @Author: xugw
 * @Date: 2021/1/11 - 16:13
 */


public class QuartzJobFactory extends QuartzJobBean {

    private static final Logger logger = LoggerFactory.getLogger(QuartzJobFactory.class);

    @Autowired
    KettleRpc kettleRpc;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        ScheduleJobEntity scheduleJob = (ScheduleJobEntity) context.getMergedJobDataMap().get("scheduleJob");
        String beanName = scheduleJob.getBeanName();
        Long jobId = scheduleJob.getJobId();
        AbstractJob job = null;
        try {
            job = ApplicationContextUtil.getBean(beanName, AbstractJob.class);
        } catch (BeansException e) {
            logger.error("find bean error", e);
        }
        if (job != null) {
            job.doExecute(jobId);
        }
    }
}
