package com.xugw.kettlecore.service;

import com.xugw.kettlecore.domination.JobParam;
import com.xugw.kettlecore.job.JobSubmitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title: 测试
 * @Description:
 * @Author: xugw
 * @Date: 2021/1/7 - 18:03
 */
@RestController
@RequestMapping("test")
public class TestService {
    @Autowired
    JobSubmitter jobSubmitter;

    @GetMapping("test/{jobid}")
    public void test(@PathVariable Long jobid){
        JobParam jobParam = new JobParam();
        jobParam.setId(jobid);
        jobParam.setHistoryId(jobid);
        jobParam.setNeedcas(true);
        jobParam.setPath("D:\\ChinaNet\\temptest\\demo.kjb");
        List<String> args = new ArrayList<>();
        jobSubmitter.submit(jobParam);
    }

    @GetMapping("interrupt/{jobid}")
    public void interrupt(@PathVariable Long jobid){
        jobSubmitter.interrupt(jobid);
    }
}
