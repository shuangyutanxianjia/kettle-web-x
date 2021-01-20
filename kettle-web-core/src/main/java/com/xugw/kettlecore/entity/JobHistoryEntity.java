package com.xugw.kettlecore.entity;

/**
 * @Title: job历史记录实体类
 * @Description:
 * @Author: xugw
 * @Date: 2021/1/12 - 15:40
 */
public class JobHistoryEntity extends BaseEntity{
    private Long jobId;
    private Integer status;
    private String log_txt;

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getLog_txt() {
        return log_txt;
    }

    public void setLog_txt(String log_txt) {
        this.log_txt = log_txt;
    }

    @Override
    public String toString() {
        return "JobHistoryEntity{" +
                "jobId=" + jobId +
                ", status='" + status + '\'' +
                ", log_txt='" + log_txt + '\'' +
                '}';
    }
}
