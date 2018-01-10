package com.cqjtu.controller;

import com.cqjtu.config.FileConfig;
import com.cqjtu.domain.Zfile;
import com.cqjtu.messages.Message;
import com.cqjtu.tools.LoggerTool;
import com.cqjtu.tools.Md5Tool;
import javafx.scene.chart.ValueAxis;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/1/10.
 */
@RestController
public class FileController {

    private FileConfig config = new FileConfig();

    @RequestMapping(value = "/fileUpload")
    public Message uploadFile(HttpServletRequest request) throws Exception {
        Message message = new Message();
        //判断是否是文件
        if (ServletFileUpload.isMultipartContent(request)){

            //设置日期目录
            String datePath = new SimpleDateFormat("yyyyMMDD").format(new Date());
            File homeFile = new File(config.getHomePath()+File.pathSeparator+datePath);
            if (!homeFile.exists()){
                homeFile.mkdirs();
            }
            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
            //设置缓冲区大小
            diskFileItemFactory.setSizeThreshold(Integer.parseInt(config.getTempSize()));
            //设置文件夹
            diskFileItemFactory.setRepository(homeFile);
            ServletFileUpload fileUpload=new ServletFileUpload(diskFileItemFactory);
            //设置最大文件大小
            fileUpload.setFileSizeMax(Integer.parseInt(config.getMaxFileSize()));
            //获取所有表单
            List<FileItem> fileItems = fileUpload.parseRequest(request);
            for (FileItem item:fileItems){
                //判断当前的表单控件是否是一个普通控件
                if (!item.isFormField()){
                    if (item.getSize()>Integer.parseInt(config.getMaxFileSize())){
                        message.setCode(405);
                        message.setInfo("文件过大，最大允许"+config.getMaxFileSize());
                    }else {
                        Zfile zfile = new Zfile();
                        //是一个文件控件时
                        zfile.setFileName(item.getName());
                        zfile.setSize(item.getSize());
                        zfile.setFileType(item.getName().substring(item.getName().lastIndexOf(".")));
                        String filePath = config.getHomePath()+File.pathSeparator+datePath+File.pathSeparator+
                                UUID.randomUUID().toString().replaceAll("-","")+"."+zfile.getFileType();
                        zfile.setFilePath(filePath);
                        item.write(new File(filePath));
                        zfile.setMd5(Md5Tool.fileMd5(new File(filePath)));
                        LoggerTool.getLogger(FileController.class).info("上传文件名:"+zfile.getFileName()+"     上传文件大小:"
                                +zfile.getSize()+"      文件类型:"+zfile.getFileType()+"       新的文件路径:"
                                +zfile.getFilePath()+"   md5值:"+zfile.getMd5());
                    }
                }
            }
        }

        return message;
    }

}
