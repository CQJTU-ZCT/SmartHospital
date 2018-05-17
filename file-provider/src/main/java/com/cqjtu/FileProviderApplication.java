package com.cqjtu;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/1/9.
 */
@EnableHystrix
@SpringBootApplication
@EnableEurekaClient
@EnableAutoConfiguration
@ComponentScan("com.cqjtu")
@ServletComponentScan
@MapperScan(value = {"com.cqjtu.mapper","com.cqjtu.mapperexp"})
public class FileProviderApplication {

    public static void main(String [] args){
        SpringApplication.run(FileProviderApplication.class,args);
    }
}
