package com.cqjtu.controller;

import com.cqjtu.mapper.HospitalMapper;
import com.cqjtu.messages.Message;
import com.cqjtu.model.Hospital;
import com.cqjtu.service.HospitalService;
import com.cqjtu.tools.AdminToken;
import com.cqjtu.tools.MailTool;
import com.cqjtu.tools.Token;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.List;
import java.util.UUID;

/**
 * @author zjhfyq
 * @Desc 医院控制器
 * @date 2018/1/25.
 */
@RestController
@RequestMapping("/hospital")
public class HospitalController {


    private String lastTimeTag = "lastTime";

    @Value("${sendTokenToAdminMail.adminMail}")
    private String adminMail;

    @Value("${sendTokenToAdminMail.keepTime}")
    private String keepTimeString;

    @Value("${sendTokenToAdminMail.sendMail}")
    private String sendMail;

    @Value("${sendTokenToAdminMail.sendPass}")
    private String sendPass;

    @Value("${sendTokenToAdminMail.smtpServer}")
    private String smtpServer;


    @Value("${pageInfo.pageSize}")
    private String pageSizeString;

    @Value("${pageInfo.navigatePages}")
    private String navigatePagesString;

    @Autowired
    private HospitalService hospitalService;





    @RequestMapping(value = {"/",""},method = {RequestMethod.POST})
    public Message registerHospitalByHeaderToken(Hospital hospital,
                                                 String adminToken){
        Message message = new Message();
        if (adminToken == null || adminToken.length() <=0){
            message.setInfo("请提供管理员令牌");
        }else{
            validateAdminToken(message,hospital,adminToken);
        }
        return message;
    }




    @RequestMapping(value = {"","/"},
            method = RequestMethod.GET)
    public Message getHospitals(HttpServletRequest request,
                                String pageNum,
                                String name,
                                String address){
        Message message = new Message();
        String token = request.getHeader("token");
        //如果没带pageNum  则默认为1
        getHospitalsAndValidate(message,pageNum,token,name,address);
        return message;
    }





    /**
     *
     * @param message 消息
     * @param pn 页数
     * @param token 令牌
     * @param name 名称
     * @param address 地址
     */
    private void getHospitalsAndValidate(Message message , String pn, String token, String name, String address){
        if (token == null){
            message.setInfo("未授权");
            message.setCode(403);
        }else {
            //尝试设置配置文件中配置参数的值
            int pageNum = 1;
            try {
                pageNum = Integer.parseInt(pn);
            }catch (Exception e){

            }
            int pageSize = 10;
            try {
                pageSize = Integer.parseInt(pageSizeString);
            }catch (Exception e){
            }
            int navigatePages = 5;
            try {
                navigatePages = Integer.parseInt(navigatePagesString);
            }catch (Exception e){
            }
            //开始分页查询
            PageHelper.startPage(pageNum,pageSize);
            List<Hospital> hospitals=hospitalService.getHospitals(name,address);
            PageInfo pageInfo = new PageInfo(hospitals,navigatePages);;
            message.setCode(200);
            message.setInfo("获取医院信息成功");
            message.put("pageInfo",pageInfo);
        }
    }




    private void validateAdminToken(Message message,Hospital hospital,String adminToken){
        if (AdminToken.token == null || AdminToken.token.length() <=0){
            message.setInfo("管理员令牌失效，请重新获取");
        }else {
            if (!adminToken.equals(AdminToken.token)){
                message.setInfo("管理员令牌不正确，请联系管理员");
            }else {
                insertHospital(message,hospital);
            }
        }
    }




    private void insertHospital(Message message,Hospital hospital){
        //验证hospital
        if (hospital.getAddress() == null || hospital.getAddress().length() <=0 ||
                hospital.getHospitalName() == null || hospital.getHospitalName().length() <=0 ||
                hospital.getLatitude() == null ||
                hospital.getLongitude() == null){
            System.out.println(hospital.getAddress() + "    "+hospital.getHospitalName() + "    "
                + hospital.getLongitude() + "   "+hospital.getLatitude());
            message.setInfo("请完善医院信息后再试");
        }else {
            hospital.setHospitalId(UUID.randomUUID().toString().replaceAll("-",""));
            if (hospitalService.registerHospital(hospital)){
                message.setCode(200);
                message.setInfo("添加医院成功");
                message.put("hospital",hospital);
            }else {
                message.setInfo("添加医院失败，请检查医院信息或稍后再试...");
            }
        }
    }






}
