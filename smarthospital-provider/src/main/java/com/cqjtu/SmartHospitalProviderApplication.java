package com.cqjtu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @description: ${description}
 * @author: codezhang
 * @date: 2018-05-22 11:30
 **/

@SpringBootApplication
@EnableAutoConfiguration
@EnableEurekaClient
public class SmartHospitalProviderApplication {
    public static void main(String []args){
        SpringApplication.run(SmartHospitalProviderApplication.class,args);
    }
}
