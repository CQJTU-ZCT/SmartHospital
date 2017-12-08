package com.cqjtu.tools;

/**
 * @author zjhfyq
 * @Desc 正则表达式工具类
 * @date 2017/12/8.
 */
public class RegularTool {


    /**
     * 验证字符串是否符合邮箱格式:只允许英文字母、数字、下划线、英文句号、以及中划线组成
     * @param mail 邮箱地址
     * @return true:邮箱地址正确；false:邮箱地址错误
     */
    public static boolean isMail(String mail){
        boolean result = false;
        if (mail == null){

        }else {
            String reg ="^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
            if(mail.matches(reg)){
                result = true;
            }
        }

        return result;
    }


}
