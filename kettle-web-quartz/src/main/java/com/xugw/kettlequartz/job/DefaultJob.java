package com.xugw.kettlequartz.job;

import com.xugw.kettlequartz.rpc.KettleRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


/**
 * 默认任务实现，执行kjb任务
 *
 */
@Component
public class DefaultJob extends AbstractJob {

    @Autowired
    KettleRpc kettleRpc;

    public void doExecute(Long jobId) {
       kettleRpc.exec(jobId);
    }
}
