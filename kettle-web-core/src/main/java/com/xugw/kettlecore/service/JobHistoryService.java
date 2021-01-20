package com.xugw.kettlecore.service;

import com.xugw.kettlecore.entity.JobHistoryEntity;

/**
 * @Title: 日志记录
 * @Description:
 * @Author: xugw
 * @Date: 2021/1/12 - 17:15
 */
public interface JobHistoryService {
    void addHistorylog(JobHistoryEntity jobHistoryEntity);
}
