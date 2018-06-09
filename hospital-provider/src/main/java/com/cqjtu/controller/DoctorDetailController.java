package com.cqjtu.controller;

import com.cqjtu.messages.Message;
import com.cqjtu.model.DoctorDetail;
import com.cqjtu.model.Hospital;
import com.cqjtu.service.DoctorDetailService;
import com.cqjtu.service.DoctorService;
import com.cqjtu.tools.RegularTool;
import com.cqjtu.tools.ValidateAdminTool;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/1/27.
 */
@RequestMapping("/hospital/doctor/detail")
@RestController
public class DoctorDetailController {


    @Value("${pageInfo.pageSize}")
    private String pageSizeString;

    @Value("${pageInfo.navigatePages}")
    private String navigatePagesString;


    @Value("${hospitalAdmin.code}")
    private String adminCode;

    @Value("${hospitalDoctor.code}")
    private String doctorCode;

    @Autowired
    private DoctorDetailService doctorDetailService;


    @Autowired
    private DoctorService doctorService ;


    @RequestMapping(value = {"/",""},method =  RequestMethod.POST)
    public Message addDoctorDetail(String token , DoctorDetail doctorDetail, HttpServletRequest request){
        Message message = new Message();
        if (token == null || token.length() <=0){
            token = request.getHeader("token");
        }
        if (token == null || token.length() <=0 ){
            token = (String) request.getAttribute("token");
        }
        if (ValidateAdminTool.isAdmin(request,adminCode) || ValidateAdminTool.isAdmin(request,doctorCode)){
            validateAndOpt(token,message,doctorDetail,RequestMethod.POST);
        }else {
            message.setInfo("非管理员或医生");
            message.setCode(403);
        }

        return message;
    }


    @RequestMapping(value = {"/",""},method =  RequestMethod.PUT)
    public Message updateDoctorDetail(String token , DoctorDetail doctorDetail, HttpServletRequest request){
        Message message = new Message();
        if (token == null || token.length() <=0){
            token = request.getHeader("token");
        }
        if (token == null || token.length() <=0 ){
            token = (String) request.getAttribute("token");
        }
        if (ValidateAdminTool.isAdmin(request,adminCode) || ValidateAdminTool.isAdmin(request,doctorCode)){
            validateAndOpt(token,message,doctorDetail,RequestMethod.PUT);
        }else {
            message.setInfo("非管理员或医生");
            message.setCode(403);
        }
        return message;
    }


    @RequestMapping(value = {"/",""},method =  RequestMethod.GET)
    public Message getDoctorDetail(String token , DoctorDetail doctorDetail, HttpServletRequest request,
                                   String pageNum){
        Message message = new Message();
        if (token == null || token.length() <=0){
            token = request.getHeader("token");
        }
        if (token == null || token.length() <=0 ){
            token = (String) request.getAttribute("token");
        }
        validateAndGet(token,message,doctorDetail,pageNum);
        return message;
    }



    private void validateAndOpt(String token ,Message message ,DoctorDetail doctorDetail ,
                                RequestMethod method){
        String info="";
        if (token == null || token.length() <=0){
            info = "未授权";
            message.setCode(403);
        }else {
            boolean flag = true;
            if (method.equals(RequestMethod.POST)){
                if (doctorDetail.getIdCard() == null || doctorDetail.getIdCard().length()<=0){
                    info = "身份证号码不能为空";
                    flag = false;
                }else {
                    if (!RegularTool.isIdCard(doctorDetail.getIdCard())){
                        info = "身份证号码格式不正确";
                        flag = false;
                    }
                }
                if (doctorService.queryDoctorByIdCard(doctorDetail.getIdCard())  == null){
                    info = "身份证号码不存在";
                    flag = false;
                }
                if (flag){
                    if (doctorDetail.getAddress() == null || doctorDetail.getAddress().length() <=0){
                        info = "地址信息不能为空";
                        flag = false;
                    }
                }
                if (flag){
                    if (doctorDetail.getNationId() == null || doctorDetail.getNationId()<=0){
                        info = "民族信息不能为空";
                        flag = false;
                    }
                }
                if (flag){
                    if (doctorDetail.getBirthYMD()  == null){
                        info = "出生年月日不能为空";
                        flag = false;
                    }
                }
                if (flag){
                    DoctorDetail queryDoctor = new DoctorDetail();
                    queryDoctor.setIdCard(doctorDetail.getIdCard());
                    List<DoctorDetail> doctorDetails = doctorDetailService.queryDoctorDetail(queryDoctor);
                    if ( doctorDetail == null ||doctorDetails.size() >0 ){
                        info = "已经存在医生的详细信息，不允许重复添加";
                    }else {
                        int i = doctorDetailService.addDoctorDetail(doctorDetail);
                        if (i==1){
                            message.setCode(200);
                            info = "添加医生详细信息成功";
                        }
                    }
                }
            }else if (method.equals(RequestMethod.PUT)){
                if (doctorDetail.getIdCard() == null || doctorDetail.getIdCard().length()<=0){
                    info ="身份证号码不能为空";
                    flag = false;
                }else {
                    if (!RegularTool.isIdCard(doctorDetail.getIdCard())){
                        info = "身份证号码格式不正确";
                        flag = false;
                    }
                }
                if (flag){
                    int i = doctorDetailService.updateDoctorDetail(doctorDetail);
                    if (i==1){
                        message.setCode(200);
                        info= "修改医生详细信息成功";
                    }else {
                        info ="修改医生相信信息失败";
                    }
                }
            }
        }
        message.setInfo(info);
        message.put("doctorDetail",doctorDetail);
    }




    private void validateAndGet(String token ,Message message ,DoctorDetail doctorDetail,String pn){
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
            List<DoctorDetail> doctorDetails = doctorDetailService.queryDoctorDetail(doctorDetail);
            PageInfo pageInfo = new PageInfo(doctorDetails,navigatePages);;
            message.setCode(200);
            message.setInfo("获取医生详细信息成功");
            message.put("pageInfo",pageInfo);
        }
    }

}
