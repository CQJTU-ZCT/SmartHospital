package com.cqjtu;

import com.cqjtu.filter.ErrorFilter;
import com.cqjtu.filter.SecurityFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

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


    @Bean
    public SecurityFilter securityFilter(){
        return new SecurityFilter();
    }

    @Bean
    public ErrorFilter errorFilter(){
        return new ErrorFilter();
    }


    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}