package com.cqjtu.controller;

import com.cqjtu.messages.Message;
import com.cqjtu.model.DoctorDetail;
import com.cqjtu.model.Hospital;
import com.cqjtu.service.DoctorDetailService;
import com.cqjtu.tools.RegularTool;
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


    @Autowired
    private DoctorDetailService doctorDetailService;


    @RequestMapping(value = {"/",""},method =  RequestMethod.POST)
    public Message addDoctorDetail(String token , DoctorDetail doctorDetail, HttpServletRequest request){
        Message message = new Message();
        if (token == null || token.length() <=0){
            token = request.getHeader("token");
        }
        validateAndOpt(token,message,doctorDetail,RequestMethod.POST);
        return message;
    }


    @RequestMapping(value = {"/",""},method =  RequestMethod.PUT)
    public Message updateDoctorDetail(String token , DoctorDetail doctorDetail, HttpServletRequest request){
        Message message = new Message();
        if (token == null || token.length() <=0){
            token = request.getHeader("token");
        }
        validateAndOpt(token,message,doctorDetail,RequestMethod.PUT);
        return message;
    }


    @RequestMapping(value = {"/",""},method =  RequestMethod.GET)
    public Message getDoctorDetail(String token , DoctorDetail doctorDetail, HttpServletRequest request,
                                   String pageNum){
        Message message = new Message();
        if (token == null || token.length() <=0){
            token = request.getHeader("token");
        }
        validateAndGet(token,message,doctorDetail,pageNum);
        return message;
    }



    private void validateAndOpt(String token ,Message message ,DoctorDetail doctorDetail ,
                                RequestMethod method){
        if (token == null || token.length() <=0){
            message.setInfo("未授权");
        }else {
            boolean flag = true;
            //todo 完全角色身份认证

            if (method.equals(RequestMethod.POST)){
                if (doctorDetail.getIdCard() == null || doctorDetail.getIdCard().length()<=0){
                    flag = false;
                }else {
                    if (!RegularTool.isIdCard(doctorDetail.getIdCard())){
                        flag = false;
                    }
                }
                if (doctorDetail.getAddress() == null || doctorDetail.getAddress().length() <=0){
                    flag = false;
                }
                if (doctorDetail.getNationality() == null || doctorDetail.getNationality().length() <=0){
                    flag = false;
                }
                if (flag){
                    int i = doctorDetailService.addDoctorDetail(doctorDetail);
                    if (i==1){
                        message.setCode(200);
                        message.setInfo("添加医生详细信息成功");
                    }else {
                        message.setInfo("添加医生详细信息失败，请检查参数");
                    }
                }
            }else if (method.equals(RequestMethod.PUT)){
                if (doctorDetail.getIdCard() == null || doctorDetail.getIdCard().length()<=0){
                    flag = false;
                }else {
                    if (!RegularTool.isIdCard(doctorDetail.getIdCard())){
                        flag = false;
                    }
                }
                if (flag){
                    int i = doctorDetailService.addDoctorDetail(doctorDetail);
                    if (i==1){
                        message.setCode(200);
                        message.setInfo("修改医生详细信息成功");
                    }else {
                        message.setInfo("修改医生详细信息失败，请检查参数");
                    }
                }
            }
            if (!flag){
                message.setInfo("参数错误");
            }
            message.put("doctorDetail",doctorDetail);
        }

    }




    private void validateAndGet(String token ,Message message ,DoctorDetail doctorDetail,String pn){
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
