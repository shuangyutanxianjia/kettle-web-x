package com.xugw.kettlecore.conf;


import com.xugw.kettlecore.pool.DefaultThreadFactory;
import com.xugw.kettlecore.pool.StandardThreadExecutor;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Title: kettle基础配置
 * @Description:
 * @Author: xugw
 * @Date: 2021/1/7 - 16:47
 */
@Configuration
@EnableConfigurationProperties()
public class KettleConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(KettleConfiguration.class);

    /**
     * 初始化kettle运行环境
     */
    @PostConstruct
    public void setup() {
        try {
            logger.info("=======init kettle environment=========");
            KettleEnvironment.init();
            logger.info("=======init success=======");
        } catch (KettleException e) {
            logger.error("init kettle env error", e);
            System.exit(-1);
        }
    }

    /**
     * 销毁kettle运行环境
     */
    @PreDestroy
    public void cleanup() {
        KettleEnvironment.shutdown();
    }

    /**
     * 配置kettle任务运行线程池
     *
     * @param properties KettleJobPoolProperties
     * @return StandardThreadExecutor
     */
    @Bean
    public StandardThreadExecutor kettleJobExecutor(KettleJobPoolProperties properties) {
        return new StandardThreadExecutor(
                properties.getCoreThreads(), properties.getMaxThreads(),
                properties.getKeepAliveTimeMin(), TimeUnit.MINUTES,
                properties.getQueueCapacity(), new DefaultThreadFactory(properties.getNamePrefix()),
                new ThreadPoolExecutor.AbortPolicy()
        );
    }
}

