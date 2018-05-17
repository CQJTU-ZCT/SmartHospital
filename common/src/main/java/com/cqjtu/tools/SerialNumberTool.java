package com.cqjtu.tools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zjhfyq
 * @Desc 流水号生成策略  长度为20
 * @date 2017/12/28.
 */
public class SerialNumberTool {

    /**
     * 记录上次的时间戳
     */
    private static Long lastTimeStamp=-1L;

    /**
     * 同步序列号
     */
    private static AtomicInteger serial = new AtomicInteger(0);

    /**
     * 同步序列号的长度
     */
    private static int serialLen = 6;


    /**
     * 获取流水号
     * @return
     */
    public  static synchronized  String getSerialNumber(){
        String target = "";
        long currentTimeMillis = System.currentTimeMillis();
        if(lastTimeStamp == currentTimeMillis){
            int newSerial = serial.addAndGet(1);
            String sameLength = toSameLength(serialLen, String.valueOf(newSerial));
            target = lastTimeStamp.toString() + sameLength;
        }else {
            serial.set(0);
            lastTimeStamp = currentTimeMillis;
            int newSerial = serial.addAndGet(1);
            String sameLength = toSameLength(serialLen, String.valueOf(newSerial));
            target = lastTimeStamp.toString() + sameLength;
        }
        return target;
    }


    private static String toSameLength(int len,String source){
        String temp ="";
        if (source.length() != serialLen){
            int del = len-source.length();
            for (int i = 0; i<del ;i++){
                temp+="0";
            }
        }
        return temp+source;
    }





}
