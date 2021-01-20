package com.xugw.kettlecore.pool;

import com.xugw.kettlecore.job.JobSubmitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 优先启动线程
 *
 * 代码来自motan rpc
 *
 */
public class StandardThreadExecutor extends ThreadPoolExecutor {

    private static final Logger logger = LoggerFactory.getLogger(StandardThreadExecutor.class);

    public static final int DEFAULT_MIN_THREADS = 20;

    public static final int DEFAULT_MAX_THREADS = 200;

    /**
     *  1 minutes
     */
    public static final int DEFAULT_MAX_IDLE_TIME = 60 * 1000;

    /**
     * 正在处理的任务数
     */
    private AtomicInteger submittedTasksCount;

    /**
     * 新线程名称
     */
    private String threadname;
    /**
     * 正在进行的任务线程名称
     */
    private ConcurrentHashMap<String,Thread> taskmap = new ConcurrentHashMap<>();

    /**
     * 最大允许同时处理的任务数
     */
    private int maxSubmittedTaskCount;

    public StandardThreadExecutor(int coreThreads, int maxThreads, long keepAliveTime, TimeUnit unit,
                                  int queueCapacity, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(coreThreads, maxThreads, keepAliveTime, unit, new ExecutorQueue(), threadFactory, handler);
        ((ExecutorQueue) getQueue()).setStandardThreadExecutor(this);

        submittedTasksCount = new AtomicInteger(0);

        // 最大并发任务限制： 队列buffer数 + 最大线程数
        maxSubmittedTaskCount = queueCapacity + maxThreads;
    }

    /**
     * 设置自定义的任务名称，用于停止caskettle相关进程
     * @param command
     * @param jobname
     */
    public void execute(Runnable command,String jobname) {
        threadname = jobname;
        execute(command);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        t.setName(threadname);
        logger.info("------------------beforeexecute---------------"+Thread.currentThread().getName());
        taskmap.put(t.getName(),t);
        super.beforeExecute(t, r);
    }

    @Override
    public void execute(Runnable command) {

        int count = submittedTasksCount.incrementAndGet();

        // 超过最大的并发任务限制，进行 reject
        // 依赖的LinkedTransferQueue没有长度限制，因此这里进行控制
        if (count > maxSubmittedTaskCount) {
            submittedTasksCount.decrementAndGet();
            getRejectedExecutionHandler().rejectedExecution(command, this);
        }

        try {

            super.execute(command);
        } catch (RejectedExecutionException rx) {
            // there could have been contention around the queue
            if (!((ExecutorQueue) getQueue()).force(command)) {
                submittedTasksCount.decrementAndGet();
                getRejectedExecutionHandler().rejectedExecution(command, this);
            }
        }
    }

    //允许线程终止，针对死循环的任务
    public void interrupt(String jobname) {
        taskmap.get(jobname).interrupt();
        taskmap.remove(jobname);
    }


    public int getSubmittedTasksCount() {
        return this.submittedTasksCount.get();
    }

    public int getMaxSubmittedTaskCount() {
        return maxSubmittedTaskCount;
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        submittedTasksCount.decrementAndGet();
    }

}
