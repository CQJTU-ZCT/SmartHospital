package com.cqjtu.tools;

import com.cqjtu.model.Users;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/2/7.
 */
public class ValidateAdminTool {

    public static boolean isAdmin(HttpServletRequest request ,String adminCode){
        boolean result = false;
        try {
            if (request.getAttribute("user") != null){
                Users user = (Users) request.getAttribute("user");
                if (adminCode.equals(user.getRoleId())){
                    result = true;
                }
            }
        }catch (Exception e){

        }
        return  result;
    }
}
