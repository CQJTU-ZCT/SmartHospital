package com.cqjtu.tools;


import com.cqjtu.domain.User;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.lang.reflect.Field;


/**
 * @author zjhfyq
 * @Desc
 * @date 2017/12/2.
 */
public class MapperGenerator {




    /**
     * mapper生成器，根据传入的
     * @return
     */
    public void mapperGenerator(User user, String tableName , String primaryKey) throws Exception {
        if (tableName == null || tableName.length()<=0){
            throw new Exception("数据库表名不能为空");
        }
        if (user != null){
            Class<? extends User> userClass = user.getClass();
            /*
             * 获取所有属性
             */
            Field[] fields = userClass.getDeclaredFields();

            /**
             * 组合sql
             */
            StringBuilder sqlBuilder = new StringBuilder();
            sqlBuilder.append("\n select ");
            for (int i=0;i<fields.length;i++){
                if (i!=fields.length-1){
                    sqlBuilder.append(fields[i].getName()+",");
                }else {
                    sqlBuilder.append(fields[i].getName()+" ");
                }
            }
            sqlBuilder.append(" from "+tableName +" where "+primaryKey+"= #{value}"+"; \n");


            //创建SAXReader对象
            SAXReader reader = new SAXReader();
            //读取文件 转换成Document

            String filePath = this.getClass().getResource("/template/mapper.xml").getFile();

            Document document = reader.read(new File(filePath));
            //获取根节点元素对象
            Element root = document.getRootElement();


            //获取select节点
            Element select = root.element("select");

            select.setText(sqlBuilder.toString());




            // 创建OutputFormat对象
            OutputFormat format = OutputFormat.createPrettyPrint();
            // 设置写入流编码
            format.setEncoding("UTF-8");
            try {
                XMLWriter writer = new XMLWriter(new FileWriter(new File(filePath)));
                // 向流写入数据
                writer.write(document);
                writer.flush();
                //关闭流
                writer.close();
            } catch (Exception e) {
                throw  new Exception("文件异常");
            }
        }else {
            throw new Exception("用户实例对象不能为空");
        }
    }


}
