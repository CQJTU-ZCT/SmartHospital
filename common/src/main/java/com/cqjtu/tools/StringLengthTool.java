package com.cqjtu.tools;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/2/5.
 */
public class StringLengthTool {

    public static  String addLengthHead(String source,String flag,int length){
        if (source.length()<length){
            int del = length - source.length();
            for (int i = 0;i<del-1;i++){
                flag+=flag;
            }
            source = flag +source;
        }
        return source;
    }


}
