package com.cqjtu.tools;

/**
 * @author zjhfyq
 * @Desc 用来区分用户身份标识的工具类，区别用户提交上来的id是身份证号码，还是邮箱，还是手机号码
 * @date 2018/1/7.
 */
public class IdentityTool {

    /**
     * 辨别是否是身份证号码
     * @param str
     * @return
     */
    public  static boolean  isIdCard(String str){
        String reg18 = "^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
        String reg15="^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}$";
        int idlen15 = 15;
        int idlen18 = 18;
        boolean result = false;
        if (str.length() == idlen15){
            if (str.matches(reg15)){
                result = true;
            }
        }else if(str.length() == idlen18){
            if (str.matches(reg18)){
                result = true;
            }
        }
        return result;
    }

    /**
     * 辨别是否是手机号码
     * @param phone
     * @return
     */
    public static boolean isPhone(String phone){
        String reg = "^[1][3,4,5,7,8][0-9]{9}$";
        boolean result = false;
        if (phone != null){
            result = phone.matches(reg);
        }
        return  result;
    }

    /**
     * 辨别是否是邮箱
     * @param mail
     * @return
     */
    public static boolean isMail(String mail){
        String reg = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        boolean result = false;
        if (mail != null){
            result = mail.matches(reg);
        }
        return result;
    }

    /**
     * 辨别用户身份标识 若都不符合  都返回错误
     * @param username
     * @return
     */
    public static String identity(String username){
        String id="id";
        String phone = "phone";
        String mail = "mail";
        String error = "error";
        if (isIdCard(username)){
            return id;
        }else if (isMail(username)){
            return mail;
        }else if (isPhone(username)){
            return phone;
        }else {
            return error;
        }
    }



}
