package com.cqjtu;


import com.cqjtu.config.FileConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/1/9.
 */
@SpringBootApplication
@EnableEurekaClient
@EnableAutoConfiguration
public class FileProviderApplication {

    public static void main(String [] args){
        SpringApplication.run(FileProviderApplication.class,args);
    }
}
