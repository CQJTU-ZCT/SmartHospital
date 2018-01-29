package com.cqjtu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author zjhfyq
 * @Desc
 * @date 2017/12/28.
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableEurekaClient
@ServletComponentScan
@MapperScan({"com.cqjtu.mapper","com.cqjtu.mapperexp"})
public class FeedbackPlatformProviderApplication {
    public static void main(String [] args){
        SpringApplication.run(FeedbackPlatformProviderApplication.class,args);
    }
}
