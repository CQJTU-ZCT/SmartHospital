package com.cqjtu.tools;

import java.util.UUID;

/**
 * @author zjhfyq
 * @Desc 令牌生成工具
 * @date 2017/12/2.
 */
public class Token {


    /**
     * 获取32位无横线token
     * @return
     */
    public static String getToken32WithoutLine(){
        String token = UUID.randomUUID().toString().replaceAll("-","");
        return token;
    }


    /**
     * 获取64位无横线token
     * @return
     */
    public static String getToken64WithoutLine(){
        String token = UUID.randomUUID().toString().replaceAll("-","")
                + UUID.randomUUID().toString().replaceAll("-","");
        return token;
    }



}
