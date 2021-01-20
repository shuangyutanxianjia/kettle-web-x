package com.xugw.kettlecore.job;

import com.xugw.kettlecore.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 默认任务实现，执行kjb任务
 *
 * @author 奔波儿灞
 * @since 1.0
 */
@Component
public class DefaultJob {

    @Autowired
    private JobService jobService;

    protected void doExecute(Long jobId) {
        String execType = "KITCHEN";
        jobService.execJob(jobId,execType);
    }
}
