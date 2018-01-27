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


    public static boolean isPhone(String phone){
        boolean result = false;
        if (phone == null){

        }else {
            String reg="^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\\\d{8}$";
            if (phone.matches(reg)){
                result = true;
            }
        }
        return result;
    }


    public static  boolean isIdCard(String idCard){
        boolean result = false;
        if (idCard == null){

        }else {
            String reg18 ="^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$" ;
            String reg15= "^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}$";
            if (idCard.matches(reg18)){
                result = true;
            }else if (idCard.matches(reg15)){
                result =true;
            }
        }
        return result;
    }


}
