package com.xugw.kettlequartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Title: 启动类
 * @Description:
 * @Author: xugw
 * @Date: 2021/1/12 - 14:25
 */

@EnableFeignClients
@SpringBootApplication
@EnableEurekaClient
public class KettleQuartzApplication {
    public static void main(String [] args){
        SpringApplication.run(KettleQuartzApplication.class,args);
    }
}
