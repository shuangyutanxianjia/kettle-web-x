package com.xugw.kettlecore.job;

import com.xugw.kettlecore.domination.JobParam;
import com.xugw.kettlecore.domination.Status;
import com.xugw.kettlecore.entity.JobHistoryEntity;
import com.xugw.kettlecore.pool.StandardThreadExecutor;
import com.xugw.kettlecore.service.JobHistoryService;
import org.pentaho.di.core.logging.LogLevel;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobEntryListener;
import org.pentaho.di.job.JobListener;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 任务提交
 *
 * @author 奔波儿灞
 * @since 1.0
 */
@Component
public class JobSubmitter {

    public static final String VARIABLE_JOB_ID = "GLOBAL_JOB_ID";

    public static final String VARIABLE_JOB_HISTORY_ID = "GLOBAL_JOB_HISTORY_ID";

    public static final String THREAD_PREX = "KETTLE-CASTHREAD-";

    private static final Logger logger = LoggerFactory.getLogger(JobSubmitter.class);

    @Autowired
    private JobHistoryService jobHistoryService;

    @Autowired
    private StandardThreadExecutor kettleJobExecutor;

    @Autowired
    private JobEntryListener logJobEntryListener;

    @Autowired
    private JobListener logJobListener;



    /**
     * 执行kitchen
     * @param param JobParam
     */
    public void submit(JobParam param) {
        String Threadjobname = THREAD_PREX + param.getId();
        kettleJobExecutor.execute(() -> {
            do{
                try {
                    // 创建job
                    JobMeta meta = new JobMeta(param.getPath(), null);
                    Job job = new Job(null, meta);

                    // 日志
                    job.setLogLevel(LogLevel.ROWLEVEL);

                    // 设置变量
//                Map<String, String> variables = param.getVariables();
//                for (Map.Entry<String, String> variable : variables.entrySet()) {
//                    job.setVariable(variable.getKey(), variable.getValue());
//                }
                    // 全局job变量设置
                    job.setVariable(VARIABLE_JOB_ID, param.getId() + "");
                    // job.setVariable(VARIABLE_JOB_HISTORY_ID, param.getHistoryId() + "");

                    // 添加listener
                    job.addJobEntryListener(logJobEntryListener);
                    job.addJobListener(logJobListener);

                    // 运行job
                    job.start();
                    // 等待job执行完毕
                    job.waitUntilFinished();

            } catch (Exception e) {
                    logger.error("job submit unknown error", e);
                try {
                    JobHistoryEntity jobHistory = new JobHistoryEntity();
                    jobHistory.setJobId(param.getId());
                    jobHistory.setStatus(Status.FAILED.value());
                    jobHistory.setLog_txt(e.getMessage());
                    jobHistoryService.addHistorylog(jobHistory);
                } catch (Exception ex) {
                    // ignore
                    logger.error("job history save error", e);
                }
            }

            }

            // 任务需要重复执行，且进程未被打断时重复执行job
            while(param.isNeedcas() && !Thread.currentThread().isInterrupted());
        },Threadjobname);
    }

    /**
     * 是否在执行
     *
     * @return 执行返回true
     */
    public boolean isActive() {
        return kettleJobExecutor.getSubmittedTasksCount() > 0;
    }


    public void interrupt(Long jobId){
        String Threadjobname = THREAD_PREX + jobId;
        kettleJobExecutor.interrupt(Threadjobname);
    }

    /**
     * 执行pan
     */
    public void submitPan(JobParam param ,List<String> args){
        kettleJobExecutor.execute(() -> {
            try {
                // 创建trans
                TransMeta transmeta = new TransMeta(param.getPath());
                Trans trans = new Trans(transmeta);
                // 日志
                trans.setLogLevel(LogLevel.ROWLEVEL);
                // 设置放入自定义属性
                trans.setVariable("CUR_CYCLE","20200101");
                trans.execute( args.toArray( new String[args.size()] ));
                trans.waitUntilFinished();
            } catch (Exception e) {
                logger.error("trans submit unknown error", e);
            }
        });
    }

}
