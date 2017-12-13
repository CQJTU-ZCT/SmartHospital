package com.cqjtu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author zjhfyq
 * @Desc
 * @date 2017/12/11.
 */
@EnableAutoConfiguration
@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
public class ApiGateWayApplication {
    public static void main(String [] args){
        SpringApplication.run(ApiGateWayApplication.class,args);
    }
}