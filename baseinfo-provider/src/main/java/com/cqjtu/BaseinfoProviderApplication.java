package com.cqjtu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/1/29.
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableEurekaClient
@MapperScan({"com.cqjtu.mapperexp","com.cqjtu.mapper"})
public class BaseinfoProviderApplication {


    public static void main(String [] args){
        SpringApplication.run(BaseinfoProviderApplication.class,args);
    }
}
