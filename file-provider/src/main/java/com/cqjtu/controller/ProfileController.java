package com.cqjtu.controller;

import com.cqjtu.domain.Zfile;
import com.cqjtu.messages.Message;
import com.cqjtu.tools.LoggerTool;
import com.cqjtu.tools.Md5Tool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author zjhfyq
 * @Desc 头像控制器
 * @date 2018/1/23.
 */
@RestController
public class ProfileController {

    @Value("${smartHospital.filePath}")
    private String fileHome;

    @Value("${spring.mvc.static-path-pattern}")
    private String staticPathPattern ;

    @Value("${picture.format}")
    private String picFormats ;

    @RequestMapping(value = "/fileUpload",method = {RequestMethod.POST})
    public Message uploadProfile(@RequestParam("profile")MultipartFile[] files)  {
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

                    String []supportFormats = picFormats.split(",");
                    if (supportFormats != null){
                        boolean isSupport = false;
                        for (String f: supportFormats){
                            if (zfile.getFileType().replace(".","").equals(f)){
                                isSupport= true;
                                break;
                            }
                        }
                        if (isSupport){
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
                    }else {
                        message.setCode(205);
                        message.setInfo("不受支持的文件格式");
                        message.put("files",zfiles);
                    }
                }
            } catch (Exception e) {
                message.setCode(501);
                message.setInfo("文件服务器忙...");
                e.printStackTrace();
            }
        }
        return message;
    }



}
