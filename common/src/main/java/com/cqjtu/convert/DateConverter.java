package com.cqjtu.convert;

import com.cqjtu.tools.StringLengthTool;
import com.sun.tools.internal.ws.wsdl.document.soap.SOAPUse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/2/5.
 */
@Component
public class DateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String source) {
        try {
            if (source.length() <10){
                String[] strings = source.split("-");
                if (strings == null ||strings.length <=1){
                    strings = source.split("/");
                }
                String year = StringLengthTool.addLengthHead(strings[0],"0",4);
                String month = StringLengthTool.addLengthHead(strings[1],"0",2);
                String day = StringLengthTool.addLengthHead(strings[2],"0",2);
                source = year + "-" +month +"-"+day;
            }else if (source.length() > 10 && source.length() <19){
                String [] datetime = source.split(" ");

                String[] date = datetime[0].split("-");
                if (date == null || date.length <=1){
                    date = source.split("/");
                }
                String year = StringLengthTool.addLengthHead(date[0],"0",4);
                String month = StringLengthTool.addLengthHead(date[1],"0",2);
                String day = StringLengthTool.addLengthHead(date[2],"0",2);

                String[] time = datetime[1].split(":");
                String hh = StringLengthTool.addLengthHead(time[0],"0",4);
                String mm = StringLengthTool.addLengthHead(time[1],"0",2);
                String ss = StringLengthTool.addLengthHead(time[2],"0",2);
                source = year + "-" +month +"-"+day +" "+hh+":"+mm+":"+ss;
            }
            String pattern = source.length()==10 ? "yyyy-MM-dd" : "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            return format.parse(source);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String [] args){
        DateConverter converter = new DateConverter();
        Date date = converter.convert("198-1-01");
        System.out.println(date);
    }
}
