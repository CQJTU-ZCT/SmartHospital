package com.cqjtu;

import com.cqjtu.domain.MailServerInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

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
