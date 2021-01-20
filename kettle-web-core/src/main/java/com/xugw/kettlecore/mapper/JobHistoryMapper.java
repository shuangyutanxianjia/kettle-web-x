package com.xugw.kettlecore.mapper;

import com.xugw.kettlecore.entity.JobEntity;
import com.xugw.kettlecore.entity.JobHistoryEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Title: jobhistorymapper
 * @Description:
 * @Author: xugw
 * @Date: 2021/1/13 - 9:33
 */

@Mapper
@Repository
public interface JobHistoryMapper {
    Integer addJobHistory(JobHistoryEntity jobHistoryEntity);
}
