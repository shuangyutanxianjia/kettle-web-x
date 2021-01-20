package com.xugw.kettlecore.service.impl;

import com.xugw.kettlecore.entity.JobHistoryEntity;
import com.xugw.kettlecore.mapper.JobHistoryMapper;
import com.xugw.kettlecore.service.JobHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Title: 日志记录
 * @Description:
 * @Author: xugw
 * @Date: 2021/1/13 - 9:28
 */
@Service
public class JobHistoryServiceImpl implements JobHistoryService {

    @Autowired
    JobHistoryMapper jobHistoryMapper;

    @Override
    public void addHistorylog(JobHistoryEntity jobHistoryEntity) {
        jobHistoryMapper.addJobHistory(jobHistoryEntity);
    }
}
