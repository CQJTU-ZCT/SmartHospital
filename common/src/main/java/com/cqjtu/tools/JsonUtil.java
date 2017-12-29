package com.cqjtu.tools;

import org.json.JSONObject;

import java.lang.reflect.Field;

/**
 * @author zjhfyq
 * @Desc
 * @date 2017/12/3.
 */
public class JsonUtil {


    /**
     * json对象自动封装成javaBean
     * @param object
     * @param clazz
     * @return
     */
    public static Object praseJsonToBean(JSONObject object , Class clazz){
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
        }

        return bean;
    }

    /**
     * javaBean对象自动封装成json
     * @return
     */
    public static String praseBeanToJson(Object object){
        JSONObject jsonObject  = new JSONObject(object);
        return jsonObject.toString();
    }



}
