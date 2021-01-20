package com.xugw.kettlecore.service.impl;

import com.xugw.kettlecore.domination.JobParam;
import com.xugw.kettlecore.entity.JobEntity;
import com.xugw.kettlecore.job.JobSubmitter;
import com.xugw.kettlecore.mapper.JobMapper;
import com.xugw.kettlecore.service.JobService;
import oracle.core.lmx.CoreException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Title: job接口实现
 * @Description:
 * @Author: xugw
 * @Date: 2021/1/11 - 18:14
 */
@Component
public class JobServiceImpl implements JobService {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    JobSubmitter jobsubmit;

    @Autowired
    JobMapper jobMapper;

    @Override
    public void execJob(Long jobid, String execType) {
        JobParam jobParam = new JobParam();
        Map<String,Object> param = new HashMap<>();
        param.put("jobid",jobid);
        List<JobEntity> jobEntities = jobMapper.queryJob(param);
        if(!jobEntities.isEmpty()){
            JobEntity jobEntity = jobEntities.get(0);
            jobParam.setId(jobEntity.getId());
            jobParam.setNeedcas(false);
            jobParam.setPath(jobEntity.getPath());
        }
        jobsubmit.submit(jobParam);
    }

    @Override
    public List<JobEntity> queryJob(Map<String, Object> params) {
        return jobMapper.queryJob(params);
    }

}
