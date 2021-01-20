package com.xugw.kettlecore.job;

import com.xugw.kettlecore.domination.Status;
import com.xugw.kettlecore.entity.JobHistoryEntity;
import org.pentaho.di.core.Result;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobEntryListener;
import org.pentaho.di.job.entry.JobEntryCopy;
import org.pentaho.di.job.entry.JobEntryInterface;
import org.springframework.stereotype.Component;

/**
 * 记录job entry日志
 *
 */
@Component
public class LogJobEntryListener extends AbstractLogListener implements JobEntryListener {

    @Override
    public void beforeExecution(Job job, JobEntryCopy jobEntryCopy, JobEntryInterface jobEntryInterface) {
    }

    @Override
    public void afterExecution(Job job, JobEntryCopy jobEntryCopy, JobEntryInterface jobEntryInterface, Result result) {
        JobHistoryEntity jobHistory = getJobHistory(job);
        jobHistory.setLog_txt(result.getLogText());
        jobHistory.setStatus(Status.RUNNING.value());
        save(jobHistory);
    }

}
