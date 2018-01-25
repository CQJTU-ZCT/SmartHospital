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
import java.math.BigDecimal;
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
    private String pageSzieString;

    @Value("${pageInfo.navigatePages}")
    private String navigatePagesString;

    @Autowired
    private HospitalService hospitalService;



    //查询方式标记
    private static  String queryStringAll="queryStringAll";

    private  static  String queryStringName ="queryStringName";

    private  static String queryStringAddress= "queryStringAddress";



    @RequestMapping(value = "/register/{adminToken}",method ={ RequestMethod.POST})
    public Message registerHospitalByPathToken( Hospital hospital,@PathVariable("adminToken") String adminToken){
        Message message = new Message();
        validateAdminToken(message,hospital,adminToken);
        return message;
    }


    @RequestMapping(value = "/register",method = {RequestMethod.POST})
    public Message registerHospitalByHeaderToken(Hospital hospital,HttpServletRequest request){
        Message message = new Message();
        String adminToken = request.getHeader("adminToken");
        if (adminToken == null || adminToken.length() <=0){
            message.setInfo("请提供管理员令牌");
        }else{
            validateAdminToken(message,hospital,adminToken);
        }
        return message;
    }


    /**
     * 获取管理员动态密码
     * @return
     */
    @RequestMapping(value = "/getAdminToken",method = RequestMethod.GET)
    public Message getAdminToken(HttpServletRequest request, HttpSession session){
        Message message = new Message();
        Long lastTime = -1L;

        lastTime = (Long) session.getAttribute(lastTimeTag);
        Long keepTime = -1L;
        //设置保存时效
        if (keepTimeString !=null && keepTimeString.length() >0){
            //配置过获取令牌的最小时间间隔
            keepTime = Long.valueOf(keepTimeString);
        }else {
            //设置默认的获取令牌的最小时间间隔
            keepTime = 60*60L;
        }

        if (session.getAttribute(lastTimeTag)!= null){
            //已经获取过令牌
            if (System.currentTimeMillis() < keepTime+lastTime){
                //最小时间间隔内
                message.setCode(-1);
                message.setInfo("不能频繁获取令牌");
            }else {
                message = getLegalTokenMessgae(keepTime,message,session);
            }
        }else {
            message = getLegalTokenMessgae(keepTime,message,session);
        }
        return message;
    }



    @RequestMapping(value = "/getHospitals",method = RequestMethod.GET)
    public Message getHospitals(HttpServletRequest request){
        Message message = new Message();
        String token = request.getHeader("token");
        //如果没带pageNum  则默认为1
        getHospitalsAndValidate(message,"1",token,queryStringAll,null,null,null,null);
        return message;
    }

    @RequestMapping(value = "/getHospitals/{pageNum}",method = RequestMethod.GET)
    public Message getHospitals(HttpServletRequest request,@PathVariable("pageNum") String pageNum){
        Message message = new Message();
        String token = request.getHeader("token");
        getHospitalsAndValidate(message,pageNum,token,queryStringAll,null,null,null,null);
        return message;
    }

    /**
     * 由于sso-client在验证后，如果是合法用户，则会把token放在头部，所以实际中，这个方法并不会被使用
     * @param pageNum
     * @param token
     * @return
     */
    @RequestMapping(value = "/getHospitals/{pageNum}/{token}",method = RequestMethod.GET)
    public Message getHospitals(@PathVariable String pageNum ,@PathVariable String token){
        Message message = new Message();
        getHospitalsAndValidate(message,pageNum,token,queryStringAll,null,null,null,null);
        return message;
    }


    @RequestMapping(value = "/getHospitalByName/{name}/{pageNum}",method = RequestMethod.GET)
    public Message getHospitalByName(HttpServletRequest request,
                                     @PathVariable String name,@PathVariable String pageNum){
        Message message = new Message();
        String token = request.getHeader("token");
        getHospitalsAndValidate(message,pageNum,token,queryStringName,name,null,null,null);
        return message;
    }

    @RequestMapping(value = "/getHospitalByName/{name}",method = RequestMethod.GET)
    public Message getHospitalByName(HttpServletRequest request,
                                     @PathVariable String name){
        Message message = new Message();
        String token = request.getHeader("token");
        getHospitalsAndValidate(message,"1",token,queryStringName,name,null,null,null);
        return message;
    }






    private void getHospitalsAndValidate(Message message , String pn, String token, String queryFlag, String name, String address,
                                         BigDecimal longitude ,BigDecimal latitude){
        if (token == null){
            message.setInfo("未授权");
        }else {
            //尝试设置配置文件中配置参数的值
            int pageNum = 1;
            try {
                pageNum = Integer.parseInt(pn);
            }catch (Exception e){

            }
            int pageSize = 10;
            try {
                pageSize = Integer.parseInt(pageSzieString);
            }catch (Exception e){
            }
            int navigatePages = 5;
            try {
                navigatePages = Integer.parseInt(navigatePagesString);
            }catch (Exception e){
            }
            //开始分页查询
            PageHelper.startPage(pageNum,pageSize);
            List<Hospital> hospitals=null;
            if (queryFlag.equals(queryStringAll)){
                //查询所有
                hospitals= hospitalService.getHospitals();
            }else if(queryFlag.equals(queryStringName)){
                //根据名称查询
                hospitals = hospitalService.getHospitalByName(name);
            }else if (queryFlag.equals(queryStringAddress)){
                //根据地址查询
                hospitals = hospitalService.getHospitalByAddress(address);
            }
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

    private Message getLegalTokenMessgae(Long keepTime,Message message,HttpSession session){
        String token = Token.getToken32WithoutLine();
        //还原之前的值
        AdminToken.reduction();
        //设置新的值
        AdminToken.keepTime = keepTime;
        AdminToken.sendTime = System.currentTimeMillis();
        AdminToken.adminMail = adminMail;
        AdminToken.token = token;
        //保存这次获取令牌的时间
        session.setAttribute(lastTimeTag,AdminToken.sendTime);
        //发送邮件
        MailTool.sendMail(adminMail,"临时令牌:"+token,"令牌",sendMail,sendPass,smtpServer);
        message.setCode(1);
        message.setInfo("成功获取令牌，请去管理员邮箱查看");
        //开启线程去监督token的时效
        AdminToken.AdminTokenLife tokenLife = new AdminToken.AdminTokenLife();
        tokenLife.start();
        return  message;
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
