package com.xugw.register.center;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Title: 注册中心
 * @Description:
 * @Author: xugw
 * @Date: 2021/1/11 - 12:57
 */

@EnableEurekaServer
@SpringBootApplication
public class RegisterCenterApplication {

    public static void main(String [] args){
        SpringApplication.run(RegisterCenterApplication.class,args);
    }

}
