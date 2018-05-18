package com.cqjtu.preorderprovider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.cqjtu")
@SpringBootApplication
@EnableAutoConfiguration
@EnableEurekaClient
@ServletComponentScan
@MapperScan(value = {"com.cqjtu.mapper","com.cqjtu.mapperexp"})
public class PreorderProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(PreorderProviderApplication.class, args);
	}
}
