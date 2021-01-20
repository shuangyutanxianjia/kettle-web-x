package com.xugw.kettlequartz.domain;

import org.springframework.stereotype.Component;

/**
 * @Title: ScheduleJob
 * @Description:
 * @Author: xugw
 * @Date: 2021/1/14 - 11:58
 */
@Component
public class ScheduleJobEntity extends BaseEntity {

    private Long jobId;

    private String cron;

    private String description;

    private String beanName;

    private Boolean scheduleStatus;

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public Boolean getScheduleStatus() {
        return scheduleStatus;
    }

    public void setScheduleStatus(Boolean scheduleStatus) {
        this.scheduleStatus = scheduleStatus;
    }

    @Override
    public String toString() {
        return "ScheduleJobEntity{" +
                "jobId=" + jobId +
                ", cron='" + cron + '\'' +
                ", description='" + description + '\'' +
                ", beanName='" + beanName + '\'' +
                ", scheduleStatus=" + scheduleStatus +
                '}';
    }
}
