package com.cqjtu.beans;

import com.cqjtu.convert.DateConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/2/5.
 */
@Configuration
public class ConvertBean {

    @Bean("conversionService")
    public ConversionServiceFactoryBean getConversionServiceFactoryBean(){
        ConversionServiceFactoryBean bean= new ConversionServiceFactoryBean();
        Set<DateConverter> converters = new HashSet<>();
        converters.add(new DateConverter()) ;
        bean.setConverters(converters);
        return bean;
    }
}
