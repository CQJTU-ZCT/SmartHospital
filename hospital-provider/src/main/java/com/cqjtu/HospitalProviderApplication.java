package com.cqjtu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/1/7.
 */
@EnableAutoConfiguration
@EnableEurekaClient
@SpringBootApplication
public class HospitalProviderApplication {

    public static void main(String [] args){
        SpringApplication.run(HospitalProviderApplication.class ,args);
    }
}