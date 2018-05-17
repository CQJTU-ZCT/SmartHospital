package com.cqjtu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import com.cqjtu.tools.MybatisGenerator;
import org.springframework.context.annotation.Bean;

/**
 * @author zjhfyq
 * @Desc
 * @date 2017/12/28.
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableEurekaClient
public class MedicalProgressManagementProviderApplication {

    public static void main(String [] args) throws Exception {
        MybatisGenerator.main(args);
        SpringApplication.run(MedicalProgressManagementProviderApplication.class,args);
    }






}
