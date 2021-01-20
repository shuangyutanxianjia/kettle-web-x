package com.xugw.kettlecore.entity;

import java.sql.Date;

/**
 * @Title: job实体类
 * @Description:
 * @Author: xugw
 * @Date: 2021/1/12 - 15:29
 */
public class JobEntity extends BaseEntity{
    private String name;
    private String description;
    private String path;
    private Long groupid;
    private String exectype;
    private Boolean jobstatus;

    public Boolean getJobstatus() {
        return jobstatus;
    }

    public void setJobstatus(Boolean jobstatus) {
        this.jobstatus = jobstatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getGroupid() {
        return groupid;
    }

    public void setGroupid(Long groupid) {
        this.groupid = groupid;
    }

    public String getExectype() {
        return exectype;
    }

    public void setExectype(String exectype) {
        this.exectype = exectype;
    }

    @Override
    public String toString() {
        return "JobEntity{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", path='" + path + '\'' +
                ", groupid=" + groupid +
                ", exectype='" + exectype + '\'' +
                ", jobstatus=" + jobstatus +
                '}';
    }
}
