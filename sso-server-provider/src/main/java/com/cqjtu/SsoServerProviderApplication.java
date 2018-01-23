package com.cqjtu;


import com.cqjtu.model.Users;
import com.cqjtu.tools.MapperGenerator;
import com.cqjtu.tools.TokenData;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author zjhfyq
 * @Desc
 * @date 2017/12/26.
 */
@EnableEurekaClient
@SpringBootApplication
@EnableAutoConfiguration
@MapperScan("com.cqjtu.mapper")
public class SsoServerProviderApplication {

    public static void main(String [] args){
        /*
        MapperGenerator generator = new MapperGenerator();
        try {
            generator.mapperGenerator(new Users(),"users","id_card");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }*/
        Thread tokenLife =new  TokenData.TokenLife();
        tokenLife.start();
        SpringApplication.run(SsoServerProviderApplication.class,args);
    }
}
