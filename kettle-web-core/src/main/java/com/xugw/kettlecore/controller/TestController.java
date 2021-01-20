package com.xugw.kettlecore.controller;

import com.xugw.kettlecore.entity.JobEntity;
import com.xugw.kettlecore.mapper.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

/**
 * @Title: 测试
 * @Description:
 * @Author: xugw
 * @Date: 2021/1/12 - 16:33
 */
@RestController
public class TestController {

    @Autowired
    JobMapper jobMapper;

    @RequestMapping("/insert/{jobid}")
    public String insert(@PathVariable Long jobid){
        JobEntity jobEntity = new JobEntity();
        jobEntity.setJobstatus(false);
        jobEntity.setGroupid(jobid);
        jobEntity.setExectype("kitchen");
        jobEntity.setName("test");
        jobEntity.setDescription("测试哦");
        jobEntity.setPath("D:\\sssss");
        java.util.Date date = new java.util.Date();
        jobMapper.addJob(jobEntity);
        return "ok";
    }
}
