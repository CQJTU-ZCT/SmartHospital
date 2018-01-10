package com.cqjtu.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/1/10.
 */
@Configuration
@PropertySource(value = {"classpath:file.properties"},encoding="utf-8")
public class FileConfig {
    @Value("homePath")
    private String homePath;
    @Value("tempSize")
    private String tempSize;
    @Value("maxFileSize")
    private String maxFileSize;


    public String getHomePath() {
        return homePath;
    }

    public void setHomePath(String homePath) {
        this.homePath = homePath;
    }

    public String getTempSize() {
        return tempSize;
    }

    public void setTempSize(String tempSize) {
        this.tempSize = tempSize;
    }

    public String getMaxFileSize() {
        return maxFileSize;
    }

    public void setMaxFileSize(String maxFileSize) {
        this.maxFileSize = maxFileSize;
    }
}
