package com.cqjtu.controller;

import com.cqjtu.domain.Zfile;
import com.cqjtu.messages.Message;
import com.cqjtu.model.Picture;
import com.cqjtu.model.Users;
import com.cqjtu.service.ProfileService;
import com.cqjtu.tools.LoggerTool;
import com.cqjtu.tools.Md5Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    
    @Autowired
    private ProfileService profileService;

    @RequestMapping(value = "/profileUpload",method = {RequestMethod.POST})
    public Message uploadProfile(@RequestParam("profile")MultipartFile file, HttpServletRequest request)  {
        if (request.getAttribute("user") == null){
            Message nullMessage = new Message();
            nullMessage.setCode(403);
            nullMessage.setInfo("未授权");
            return nullMessage;
        }
        Message message = new Message();
        if (file == null || file.getOriginalFilename() == null
                || file.getOriginalFilename().length() <=0){
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
            try {
                    Zfile zfile = new Zfile();
                    //是一个文件控件时
                    zfile.setFileName(file.getOriginalFilename());
                    zfile.setSize(file.getSize());
                    zfile.setFileType(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));

                    String []supportFormats = picFormats.split(",");
                    if (supportFormats.length > 0){
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
                            //将Zfile存进数据库
                            Picture picture = new Picture();
                            Users user= (Users) request.getAttribute("user");
                            picture.setPictureId(user.getIdCard());
                            picture.setPicturePath(zfile.getFilePath());
                            boolean addResult = profileService.addProfile(picture);
                            if (addResult){
                                message.setCode(200);
                                message.setInfo("文件上传成功");
                                message.put("profile",zfile);
                            }else {
                                message.setCode(501);
                                message.setInfo("文件上传过程中出现未知错误，请稍后再试");
                            }
                        }
                    }else {
                        message.setCode(205);
                        message.setInfo("不受支持的文件格式");
                        message.put("profile",zfile);
                    }
            } catch (Exception e) {
                message.setCode(501);
                message.setInfo("文件服务器忙...");
                e.printStackTrace();
            }
        }
        return message;
    }


    @RequestMapping(value = "/getProfile",method = RequestMethod.GET)
    public Message getProfile(HttpServletRequest request){
        Message message  = new Message();
        if (request.getAttribute("user")== null){
            message.setInfo("未授权，请登录后再试");
            message.setCode(403);
        }else {
            Users user = (Users)request.getAttribute("user");
            if (user.getIdCard() == null || user.getIdCard().length() <=0 ){
                message.setCode(203);
                message.setInfo("登录信息失效，请重新登录");
            }else {
                Picture profile = profileService.getProfile(user.getIdCard());
                message.setCode(200);
                message.setInfo("获取头像成功");
                message.put("profile",profile);
            }
        }
        return  message;
    }

}
