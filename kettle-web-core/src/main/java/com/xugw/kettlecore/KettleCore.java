package com.xugw.kettlecore;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @Title: kettlecore启动
 * @Description:
 * @Author: xugw
 * @Date: 2021/1/11 - 13:12
 */

@EnableFeignClients
@EnableEurekaClient
@MapperScan("com.xugw.kettlecore.mapper")
@SpringBootApplication
public class KettleCore {
    public static void main(String [] args){
        SpringApplication.run(KettleCore.class,args);
    }
}
