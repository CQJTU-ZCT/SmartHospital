package com.cqjtu.tools;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


/**
 * @author zjhfyq
 * @Desc
 * @date 2017/12/3.
 */
public class JsonUtil {


    /**
     * json对象自动封装成javaBean
     * @param json
     * @param clazz
     * @return
     */
    public static Object praseJsonToBean(String json , Class clazz){
        /*
        Field[] fields = clazz.getDeclaredFields();
        Object bean = null;
        try {
            bean = clazz.newInstance();
            for (Field field : fields){
                String name = field.getName();
                field.setAccessible(true);
                field.set(bean , object.get(name));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        /*
        JSON js = JSON.parseObject(json);
        Object bean =js.toJavaObject(clazz);
        */
        return JSON.parseObject(json,clazz);
    }

    /**
     * javaBean对象自动封装成json
     * @return
     */
    public static String praseBeanToJson(Object object){
        return JSON.toJSONString(object);
    }



}
