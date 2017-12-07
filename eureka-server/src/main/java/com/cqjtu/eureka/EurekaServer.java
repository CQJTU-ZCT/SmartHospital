package com.cqjtu.eureka;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author zjhfyq
 * @Desc
 * @date 2017/12/5.
 */
@EnableAutoConfiguration
@EnableEurekaServer
@SpringBootApplication
public class EurekaServer {

    public static void main(String [] args){
        SpringApplication.run(EurekaServer.class,args);
    }
}
