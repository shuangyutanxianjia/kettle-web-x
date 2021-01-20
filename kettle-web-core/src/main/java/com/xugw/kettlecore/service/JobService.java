package com.xugw.kettlecore.service;

import com.xugw.kettlecore.entity.JobEntity;

import java.util.List;
import java.util.Map;

/**
 * @Title: job实现接口类
 * @Description:
 * @Author: xugw
 * @Date: 2021/1/11 - 17:51
 */
public interface JobService {

    /**
     * job执行
     * @param jobid 任务ID
     * @param execType 执行任务类型 kitchen / pan
     */
    void execJob(Long jobid, String execType);

    /**
     * 查询job
     * @param
     */
    List<JobEntity> queryJob(Map<String, Object> params);


}
