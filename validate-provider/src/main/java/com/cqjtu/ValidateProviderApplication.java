package com.cqjtu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author zjhfyq
 * @Desc
 * @date 2017/12/8.
 */
@EnableEurekaClient
@SpringBootApplication
@EnableAutoConfiguration
public class ValidateProviderApplication {
    public static void main(String [] args){
        SpringApplication.run(ValidateProviderApplication.class,args);
    }
}
