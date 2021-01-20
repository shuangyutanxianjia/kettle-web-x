package com.xugw.kettlecore.nettyrpc;

import com.xugw.kettlecore.entity.JobEntity;
import com.xugw.kettlecore.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: 远程执行调用
 * @Description:
 * @Author: xugw
 * @Date: 2021/1/14 - 11:41
 */
@RestController
@RequestMapping("exec")
public class ExecRpc {
    @Autowired
    JobService jobService;

    @PutMapping("/{jobId:\\d+}")
    String exec(@PathVariable("jobId") Long jobId){
        Map<String,Object> params = new HashMap<>();
        params.put("jobid",jobId);
        List<JobEntity> jobEntities = jobService.queryJob(params);
        if(jobEntities.isEmpty()){
            return "未找到需执行的任务";
        }
        jobService.execJob(jobId,"Kitchen");
        return "任务已启动";
    }
}
