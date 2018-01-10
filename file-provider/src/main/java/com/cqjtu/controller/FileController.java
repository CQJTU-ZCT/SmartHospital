package com.cqjtu.controller;

import com.cqjtu.config.FileConfig;
import com.cqjtu.domain.User;
import com.cqjtu.domain.Zfile;
import com.cqjtu.messages.Message;
import com.cqjtu.tools.LoggerTool;
import com.cqjtu.tools.Md5Tool;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private FileConfig fileConfig ;



    @RequestMapping(value = "/fileUpload",method = {RequestMethod.POST})
    public Message uploadFile(@RequestParam("files")MultipartFile [] files)  {
        //User user = (User)request.getAttribute("user");
        Message message = new Message();
        if (files == null || files.length <= 0){
            message.setCode(203);
            message.setInfo("没有文件");
        }else {
            //设置日期目录
            String datePath = new SimpleDateFormat("yyyyMMDD").format(new Date());
            File homeFile = new File(fileConfig.getHomePath()+File.separator+datePath);
            if (!homeFile.exists()){
                homeFile.mkdirs();
            }
            List<Zfile> zfiles= new ArrayList<>();
            for (MultipartFile file :files){
                Zfile zfile = new Zfile();
                //是一个文件控件时
                zfile.setFileName(file.getOriginalFilename());
                zfile.setSize(file.getSize());
                //zfile.setUserId(user.getUsername());
                zfile.setFileType(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));
                String filePath = fileConfig.getHomePath()+File.separator+datePath+File.separator+
                        UUID.randomUUID().toString().replaceAll("-","")+zfile.getFileType();
                zfile.setFilePath(filePath);
                try {
                    file.transferTo(new File(filePath));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                zfile.setMd5(Md5Tool.fileMd5(new File(filePath)));
                LoggerTool.getLogger(FileController.class).info("上传文件名:"+zfile.getFileName()+"     上传文件大小:"
                        +zfile.getSize()+"      文件类型:"+zfile.getFileType()+"       新的文件路径:"
                        +zfile.getFilePath()+"   md5值:"+zfile.getMd5());
                //TODO 将Zfile存进数据库
                zfiles.add(zfile);
            }
            message.setCode(200);
            message.setInfo("文件上传成功");
            message.put("files",zfiles);
        }
        return message;
    }

}
