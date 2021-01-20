package com.xugw.kettlecore.mapper;

import com.xugw.kettlecore.entity.JobEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Title: jobmapper
 * @Description:
 * @Author: xugw
 * @Date: 2021/1/12 - 15:56
 */

@Mapper
@Repository
public interface JobMapper {
    Integer addJob(JobEntity jobEntity);

    List<JobEntity> queryJob(Map<String, Object> params);
}
