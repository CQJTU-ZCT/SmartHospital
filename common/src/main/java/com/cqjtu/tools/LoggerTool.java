package com.cqjtu.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zjhfyq
 * @Desc 日志工具
 * @date 2018/1/9.
 */
public class LoggerTool {

    public static Logger getLogger(Class clazz){
        return LoggerFactory.getLogger(clazz);
    }
}
