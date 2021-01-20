package com.xugw.kettlequartz.rpc;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * @Title: 远程调用接口
 * @Description:
 * @Author: xugw
 * @Date: 2021/1/14 - 11:36
 */
@FeignClient(value = "kettle-web-core", path = "/exec")
@Component
public interface KettleRpc {

    @PutMapping("/{jobId}")
    String exec(@PathVariable("jobId") Long jobId);
}
