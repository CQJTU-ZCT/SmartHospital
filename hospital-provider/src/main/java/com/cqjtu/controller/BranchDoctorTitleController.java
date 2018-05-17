package com.cqjtu.controller;

import com.cqjtu.messages.Message;
import com.cqjtu.model.BranchDoctorTitle;
import com.cqjtu.modelexp.BranchDoctorTitleExp;
import com.cqjtu.service.BranchDoctorTitleService;
import com.cqjtu.tools.ValidateAdminTool;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
 * @date 2018/1/26.
 */

@RestController
@RequestMapping("/branch-doctor-title")
public class BranchDoctorTitleController {


    @Value("${hospitalAdmin.code}")
    private String adminCode;


    @Value("${pageInfo.pageSize}")
    private String pageSizeString;

    @Value("${pageInfo.navigatePages}")
    private String navigatePagesString;



    @Autowired
    private BranchDoctorTitleService branchDoctorTitleService;


    @RequestMapping(value = {"","/"},method = RequestMethod.POST)
    public Message addBranchDoctorPosition(String token,
                                           HttpServletRequest request ,
                                           BranchDoctorTitle branchDoctorTitle){
        Message message = new Message();
        if (token == null){
            token = request.getHeader("token");
        }
        if (ValidateAdminTool.isAdmin(request,adminCode)){
            validateAndOpt(token,message,RequestMethod.POST,branchDoctorTitle);
        }else {
            message.setCode(403);
            message.setInfo("非管理员");
        }

        return  message;
    }




    @RequestMapping(value = {"","/"},method = RequestMethod.PUT)
    public Message updateBranchDoctorPosition(String token,
                                           HttpServletRequest request ,
                                              BranchDoctorTitle branchDoctorTitle){
        Message message = new Message();
        if (token == null){
            token = request.getHeader("token");
        }
        if (ValidateAdminTool.isAdmin(request,adminCode)){
            validateAndOpt(token,message,RequestMethod.PUT,branchDoctorTitle);
        }else {
            message.setInfo("非管理员");
            message.setCode(403);
        }

        return  message;
    }


    private void validateAndOpt(String token ,Message message ,RequestMethod method,BranchDoctorTitle branchDoctorTitle){
        String info ="";
        if (token == null || token.length() <=0){
            info = "未授权";
            message.setCode(403);
        }else {
            boolean flag = true;
            if (method.equals(RequestMethod.POST)){
                //添加操作
                if (branchDoctorTitle.getBranchId() == null || branchDoctorTitle.getBranchId() <=0){
                    info = "科室编号不能为空";
                    flag = false;
                }
                if (branchDoctorTitle.getTitleId() == null || branchDoctorTitle.getTitleId() <=0){
                    info = "职称编号不能为空";
                    flag = false;
                }
                if (branchDoctorTitle.getIdCard() == null || branchDoctorTitle.getIdCard().length() <=0){
                    info = "用户标识不能为空";
                    flag = false;
                }
                if (flag){
                    int add = branchDoctorTitleService.addBranchDoctorTitile(branchDoctorTitle);
                    if (add >0 ){
                        message.setCode(200);
                        info="添加科室医生职称信息成功";
                        branchDoctorTitle.setBdtId(add);
                    }else {
                        info = "添加科室医生职称信息失败";
                    }
                }
            }else if (method.equals(RequestMethod.PUT)){
                int paraNum =0;
                //修改操作
                if (branchDoctorTitle.getBdtId() == null||branchDoctorTitle.getBdtId() <=0 ){
                    info = "科室医生职称编号不能为空";
                    flag =false;
                }
                if (flag){
                    if (branchDoctorTitle.getBranchId() != null && branchDoctorTitle.getBranchId() >0){
                        paraNum++;
                    }
                    if (branchDoctorTitle.getTitleId() != null && branchDoctorTitle.getTitleId() >0){
                        paraNum++;
                    }
                    if (branchDoctorTitle.getIdCard() != null && branchDoctorTitle.getIdCard().length() >0){
                        paraNum++;
                    }
                }
                if (flag){
                    if (paraNum >= 1){int update = branchDoctorTitleService.updateBranchDoctorTitle(branchDoctorTitle);
                        if (update == 1 ){
                            message.setCode(200);
                            info = "修改科室医生职称信息成功";
                        }else {
                            info = "修改科室医生职称信息失败";
                        }
                    }else {
                        info = "要修改的信息不能为空";
                    }
                }
            }
        }
        message.setInfo(info);
        message.put("branchDoctorTitle",branchDoctorTitle);
    }


    @RequestMapping(value = {"","/"},method = RequestMethod.GET)
    public Message getBranchDoctorPosition(String token,BranchDoctorTitle branchDoctorTitle,
                                           HttpServletRequest request,String pageNum){
        Message message =new Message();
        if (token == null || token.length() <=0){
            token = request.getHeader("token");
        }
        validateAndGet(message,token,branchDoctorTitle,pageNum);
        return  message;
    }


    private void validateAndGet(Message message ,String token ,BranchDoctorTitle branchDoctorTitle ,String pn){
        if (token == null ||token.length() <=0){
            message.setInfo("未授权");
            message.setCode(401);
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
            List<BranchDoctorTitleExp> branchDoctorTitles = branchDoctorTitleService.queryBranchDoctorTitle(branchDoctorTitle);
            PageInfo pageInfo = new PageInfo(branchDoctorTitles,navigatePages);
            message.setCode(200);
            message.setInfo("获取科室医生职称信息成功");
            message.put("pageInfo",pageInfo);
        }
    }







}
