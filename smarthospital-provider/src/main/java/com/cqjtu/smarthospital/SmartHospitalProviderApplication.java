package com.cqjtu.smarthospital;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @description: ${description}
 * @author: codezhang
 * @date: 2018-05-22 11:30
 **/

@SpringBootApplication
@EnableAutoConfiguration
@MapperScan("com.cqjtu.smarthospital.mapper")
@ComponentScan("com.cqjtu.smarthospital")
public class SmartHospitalProviderApplication {
    public static void main(String []args){
        SpringApplication.run(SmartHospitalProviderApplication.class,args);
    }
}
