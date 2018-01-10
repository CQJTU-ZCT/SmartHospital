package com.cqjtu;


import com.cqjtu.config.FileConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/1/9.
 */
@SpringBootApplication
@EnableEurekaClient
@EnableAutoConfiguration
@ComponentScan("com.cqjtu")
public class FileProviderApplication {

    public static void main(String [] args){
        SpringApplication.run(FileProviderApplication.class,args);
    }
}
