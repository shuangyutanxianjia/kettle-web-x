package com.xugw.kettlecore.job;

import com.xugw.kettlecore.entity.JobHistoryEntity;
import com.xugw.kettlecore.service.JobHistoryService;
import com.xugw.kettlecore.util.ZipUtils;
import org.pentaho.di.job.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

/**
 * log记录
 *
 * @author 奔波儿灞
 * @since 1.0
 */
public abstract class AbstractLogListener {

    @Autowired
    private JobHistoryService jobHistoryService;

    protected JobHistoryEntity getJobHistory(Job job) {
        long jobId = Long.parseLong(job.getVariable(JobSubmitter.VARIABLE_JOB_ID));
        JobHistoryEntity jobHistory = new JobHistoryEntity();
        jobHistory.setJobId(jobId);
        return jobHistory;
    }

    protected void save(JobHistoryEntity jobHistory) {
        String logText = jobHistory.getLog_txt();
        // kettle日志是反的，需要调正存储，或者前端调整
        StringBuilder sb = new StringBuilder();
        if (!StringUtils.isEmpty(logText)) {
            String[] lines = logText.split("\n");
            for (int i = lines.length - 1; i > 0; i--) {
                if ("null".equals(lines[i])) {
                    continue;
                }
                sb.append(lines[i]).append("\n");
            }
        }
        // 对日志字符串进行压缩防止过长
        jobHistory.setLog_txt(ZipUtils.gzip(sb.toString()));
        try {
            jobHistoryService.addHistorylog(jobHistory);
        }catch(Exception e){
            jobHistory.setLog_txt("日志过长，防止运行中断，不进行日志记录");
            jobHistoryService.addHistorylog(jobHistory);
        }
    }

}
