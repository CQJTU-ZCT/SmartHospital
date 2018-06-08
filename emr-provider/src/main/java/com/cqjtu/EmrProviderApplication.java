package com.cqjtu;

import com.cqjtu.mapperexp.EmrMapperExp;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zjhfyq
 * @Desc
 * @date 2017/12/28.
 */
@ServletComponentScan
@EnableAutoConfiguration
@SpringBootApplication
@EnableEurekaClient
@ComponentScan("com.cqjtu")
@MapperScan(value = {"com.cqjtu.mapper", "com.cqjtu.mapperexp"})
public class EmrProviderApplication {

    public static void main(String [] args){

        SpringApplication.run(EmrProviderApplication.class,args);
    }
}
