package com.cqjtu.controller;

import com.cqjtu.messages.Message;
import com.cqjtu.model.Doctor;
import com.cqjtu.model.Position;
import com.cqjtu.service.DoctorService;
import com.cqjtu.tools.RegularTool;
import com.cqjtu.tools.ValidateAdminTool;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.mail.imap.protocol.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Dictionary;
import java.util.List;

/**
 * @author zjhfyq
 * @Desc 医生信息控制器
 * @date 2018/1/25.
 */
@RestController
@RequestMapping("/hospital/doctor")
public class DoctorController {




    @Value("${hospitalAdmin.code}")
    private String adminCode;

    @Value("${hospitalDoctor.code}")
    private String doctorCode;

    @Value("${pageInfo.pageSize}")
    private String pageSizeString;

    @Value("${pageInfo.navigatePages}")
    private String navigatePagesString;


    @Autowired
    private DoctorService doctorService;


    @RequestMapping(value = {"","/"},method = RequestMethod.GET)
    public Message getDoctors(String token, String pageNum,
                              Doctor doctor, HttpServletRequest request){
        Message message = new Message();
        if (token == null || token.length() <=0){
            token = request.getHeader("token");
        }
        validateAndGet(token,pageNum,doctor,message);
        return message;
    }


    @RequestMapping(value = {"","/"},method = {RequestMethod.POST})
    public Message addDoctor(String token ,Doctor doctor ,HttpServletRequest request){
        Message message = new Message();
        if (token == null ||  token.length() <=0 ){
            token = request.getHeader("token");
        }
        if (ValidateAdminTool.isAdmin(request,adminCode)){
            validateAndOpt(token,doctor,RequestMethod.POST,message);
        }else {
            message.setCode(403);
            message.setInfo("非管理员");
        }
        return message;
    }



    @RequestMapping(value = {"","/"},method = {RequestMethod.PUT})
    public Message updateDoctor(String token ,Doctor doctor ,HttpServletRequest request){
        Message message = new Message();
        if (token == null ||  token.length() <=0 ){
            token = request.getHeader("token");
        }
        if (ValidateAdminTool.isAdmin(request,adminCode) || ValidateAdminTool.isAdmin(request,doctorCode)){
            validateAndOpt(token,doctor,RequestMethod.PUT,message);
        }else {
            message.setCode(403);
            message.setInfo("非管理员或医生");
        }
        return message;
    }


    @RequestMapping(value = {"/id-card","/id-card/"},method = {RequestMethod.PUT})
    public Message updateDoctorIdCard(String token  ,HttpServletRequest request,
                                      String idCard){
        Message message = new Message();
        if (token == null ||  token.length() <=0 ){
            token = request.getHeader("token");
        }else {
            if (ValidateAdminTool.isAdmin(request,adminCode) || ValidateAdminTool.isAdmin(request,doctorCode)){
                if (idCard != null && idCard.length()>0 && RegularTool.isIdCard(idCard)){
                    int i = doctorService.updateDoctorIdCard(idCard);
                    if (i ==1){
                        message.setCode(200);
                        message.setInfo("修改医生身份证号码成功");
                    }else {
                        message.setInfo("修改医生身份证号码失败");
                    }
                }else {
                    message.setInfo("参数错误，修改医生身份证号码失败");
                }
            }else {
                message.setCode(403);
                message.setInfo("非管理员或医生");
            }
            message.put("idCard",idCard);
        }
        return message;
    }




    private void validateAndOpt(String token , Doctor doctor , RequestMethod method , Message message){
        String info= "";
        if (token == null || token.length() <=0){
            info = "未授权";
            message.setCode(403);
        }else {

            boolean flag = true;
            if (method.equals(RequestMethod.POST)){
                if (doctor.getIdCard() == null || doctor.getIdCard().length() <=0){
                    info = "身份证号码不能为空";
                    flag = false;
                }else {
                    if (!RegularTool.isIdCard(doctor.getIdCard())){
                        info = "身份证号码格式不正确";
                        flag =false;
                    }
                }
                if (doctor.getMail() == null || doctor.getMail().length() <= 0){
                    info = "邮箱不能为空";
                    flag = false;
                }else {
                    if (!RegularTool.isMail(doctor.getMail())){
                        info = "邮箱格式不正确";
                        flag =false;
                    }
                }
                if (doctor.getName() == null || doctor.getName().length() <=0){
                    info = "姓名不能为空";
                    flag = false;
                }
                if (doctor.getPassword() == null || doctor.getPassword().length() <=0){
                    info = "密码不能为空";
                    flag = false;
                }
                if (doctor.getPhone() == null || doctor.getPhone().length() <=0){
                    info = "手机号码不能为空";
                    flag = false;
                }else {
                    if (!RegularTool.isPhone(doctor.getPhone())){
                        info = "手机号码格式不正确";
                        flag = false;
                    }
                }
                if (flag){
                    Doctor doctorByIdCard = doctorService.queryDoctorByIdCard(doctor.getIdCard());
                    if ( doctorByIdCard != null){
                        info = "不允许重复添加医生信息";
                    }else {
                        int i = doctorService.addDoctor(doctor);
                        if (i == 1){
                            message.setCode(200);
                            info = "添加医生信息成功";
                        }else {
                            info = "添加医生信息失败";
                        }
                    }
                }
            }else if (method.equals(RequestMethod.PUT)){
                //记录提交过来的参数的个数
                int paraNum = 0;
                if (doctor.getIdCard() == null || doctor.getIdCard().length() <=0){
                    info = "身份证号码不能为空";
                    flag = false;
                }else {
                    if (!RegularTool.isIdCard(doctor.getIdCard())){
                        info = "身份证格式不正确";
                        flag =false;
                    }
                }
                if (doctor.getMail() != null ){
                    if (!RegularTool.isMail(doctor.getMail())){
                        info = "邮箱格式不正确";
                        flag =false;
                    }
                }
                if (doctor.getPhone() != null ){
                    if (!RegularTool.isPhone(doctor.getPhone())){
                        info = "手机号码格式不正确";
                        flag = false;
                    }
                }
                if (doctor.getName() != null && doctor.getName().length() >0){
                    paraNum +=1;
                }
                if (doctor.getPassword() != null && doctor.getPassword().length() >0){
                    paraNum +=1;
                }
                if (doctor.getAccountStatusId() !=null  && doctor.getAccountStatusId() >0){
                    paraNum +=1;
                }
                if (paraNum <=0){
                    info = "更新内容不能为空";
                }else{
                    if (flag){
                        int i = doctorService.updateDoctor(doctor);
                        if (i == 1){
                            message.setCode(200);
                            info = "更新医生信息成功";
                        }else {
                            info = "更新医生信息失败";
                        }
                    }
                }

            }
        }
        message.setInfo(info);
        message.put("doctor",doctor);
    }

    private void validateAndGet(String token ,String pn ,Doctor doctor,Message message){
        if (token == null || token.length() <=0){
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
            List<Doctor> doctors = doctorService.queryDoctor(doctor);
            PageInfo pageInfo = new PageInfo(doctors,navigatePages);
            message.setCode(200);
            message.setInfo("获取医生信息成功");
            message.put("pageInfo",pageInfo);
        }
    }



}
