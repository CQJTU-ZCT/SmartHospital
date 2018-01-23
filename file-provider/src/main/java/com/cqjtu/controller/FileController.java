package com.cqjtu.controller;


import com.cqjtu.domain.Zfile;
import com.cqjtu.messages.Message;
import com.cqjtu.tools.LoggerTool;
import com.cqjtu.tools.Md5Tool;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/1/10.
 */
@RestController
public class FileController {

    @Value("${smartHospital.filePath}")
    private String fileHome;

    @Value("${spring.mvc.static-path-pattern}")
    private String staticPathPattern ;


    //@RequestMapping(value = "/fileUpload",method = {RequestMethod.POST})
    public Message uploadFile(@RequestParam("files")MultipartFile [] files)  {
        Message message = new Message();
        if (files == null || files.length <= 0){
            message.setCode(203);
            message.setInfo("没有文件");
        }else {
            //设置日期目录
            String datePath = new SimpleDateFormat("yyyyMMDD").format(new Date());
            File homeFile = null;
            if (fileHome.endsWith(File.separator)){
                homeFile = new File(fileHome+datePath);
            }else {
                homeFile = new File(fileHome+File.separator+datePath);
            }
            if (!homeFile.exists()){
                homeFile.mkdirs();
            }


            List<Zfile> zfiles= new ArrayList<>();
            try {
                for (MultipartFile file :files){
                    Zfile zfile = new Zfile();
                    //是一个文件控件时
                    zfile.setFileName(file.getOriginalFilename());
                    zfile.setSize(file.getSize());
                    zfile.setFileType(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));
                    String newFileName = UUID.randomUUID().toString().replaceAll("-","")+zfile.getFileType();
                    String filePath =fileHome+File.separator+datePath+File.separator+
                            newFileName;

                    //保存文件
                    file.transferTo(new File(filePath));
                    zfile.setMd5(Md5Tool.fileMd5(new File(filePath)));

                    //部署时  返回文件URL
                    String fileUrl = staticPathPattern.substring(0,staticPathPattern.lastIndexOf("/"))+File.separator+datePath
                            +File.separator+newFileName;
                    zfile.setFilePath(fileUrl);

                    LoggerTool.getLogger(FileController.class).info("上传文件名:"+zfile.getFileName()+"     上传文件大小:"
                            +zfile.getSize()+"      文件类型:"+zfile.getFileType()+"       新的文件路径:"
                            +zfile.getFilePath()+"   md5值:"+zfile.getMd5());
                    //TODO 将Zfile存进数据库
                    zfiles.add(zfile);
                    message.setCode(200);
                    message.setInfo("文件上传成功");
                    message.put("files",zfiles);
                }
            } catch (Exception e) {
                message.setCode(501);
                message.setInfo("文件服务器忙...");
                e.printStackTrace();
            }
        }
        return message;
    }


    //@RequestMapping(value = "/downloadFile/{fileName}")
    public Message downloadFile(@PathVariable("fileName")String fileName, HttpServletResponse response){
        Message message = new Message();
        boolean success =false;
        if (fileName == null || fileName.length() <= 0) {
            message.setCode(203);
            message.setInfo("文件名不合法");
        }else{
            //TODO 去数据库寻找文件路径
            String filePath ="";
            if (filePath == null || filePath.length() <=0){
                message.setCode(203);
                message.setInfo("文件不存在");
            }else {
                //文件存在
                File file = new File(filePath);
                // 设置强制下载不打开
                response.setContentType("application/force-download");
                // 设置文件名
                response.addHeader("Content-Disposition",
                        "attachment;fileName=" +  fileName);
                byte [] bytes = new byte[1024];
                FileInputStream fileInputStream =null;
                BufferedInputStream bis = null;
                BufferedOutputStream bos = null;
                try {
                    fileInputStream = new FileInputStream(file);
                    bis = new BufferedInputStream(fileInputStream);
                    OutputStream os = response.getOutputStream();
                    bos = new BufferedOutputStream(os);

                    int len = bis.read(bytes);
                    while (len != -1){
                        bos.write(bytes,0,len);
                        len = bis.read(bytes);
                    }
                    bis.close();
                    bos.close();
                }catch (FileNotFoundException e){

                }catch (IOException e){

                }
            }
        }
        if (success){
            return  null;
        }else {
            return  message;
        }
    }

}
