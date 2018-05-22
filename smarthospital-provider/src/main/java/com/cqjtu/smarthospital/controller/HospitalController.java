package com.cqjtu.smarthospital.controller;

import com.cqjtu.smarthospital.model.Hospital;
import com.cqjtu.smarthospital.model.Message;
import com.cqjtu.smarthospital.service.HospitalService;
import com.cqjtu.smarthospital.tools.AdminToken;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

/**
 * @description: ${description}
 * @author: codezhang
 * @date: 2018-05-22 12:01
 **/

@Controller
@ResponseBody
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
    public Message registerHospital(Hospital hospital,
                                                 String adminToken , HttpServletRequest request){
        Message message = new Message();
        message.setCode(200);
        if (adminToken == null || adminToken.length() <=0){
            adminToken = request.getHeader("adminToken");
        }
        if (adminToken == null || adminToken.length() <= 0){
            message.setInfo("请提供管理员令牌");
        }else{
            validateAdminToken(message,hospital,adminToken);
        }
        return message;
    }




    @RequestMapping(value = {"","/"},
            method = RequestMethod.GET)
    public Message getHospitals(String pageNum,
                                String name,
                                String address){
        Message message = new Message();
        //如果没带pageNum  则默认为1
        getHospitals(message,pageNum,name,address);
        return message;
    }





    /**
     *
     * @param message 消息
     * @param pn 页数
     * @param name 名称
     * @param address 地址
     */
    private void getHospitals(Message message , String pn, String name, String address){
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
        PageInfo pageInfo = new PageInfo(hospitals,navigatePages);
        message.setCode(200);
        message.setInfo("获取医院信息成功");
        message.put("pageInfo",pageInfo);
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
        System.out.println("添加医院");
        //验证hospital
        if (hospital.getAddress() == null || hospital.getAddress().length() <=0 ||
                hospital.getHospitalName() == null || hospital.getHospitalName().length() <=0 ||
                hospital.getLatitude() == null ||
                hospital.getLongitude() == null || hospital.getGateway()== null || hospital.getGateway().length() <=0){
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
