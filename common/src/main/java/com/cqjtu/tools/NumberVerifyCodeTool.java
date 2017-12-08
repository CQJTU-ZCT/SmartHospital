package com.cqjtu.tools;

import java.util.Random;

/**
 * @author zjhfyq
 * @Desc 数字验证码生成工具类
 * @date 2017/12/8.
 */
public class NumberVerifyCodeTool {


    /**
     * 获取6位数字验证码
     * @return
     */
    public static String getNumberVerifyCode(int length){
        if (length <= 0){
            return "";
        }
        String code = "";
        Random random = new Random();
        for (int i=0;i<length;i++){
            int nextInt = random.nextInt(10);
            code += nextInt;
        }
        return code;
    }


}
