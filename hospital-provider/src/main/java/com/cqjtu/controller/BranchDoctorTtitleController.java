package com.cqjtu.controller;

import com.cqjtu.messages.Message;
import com.cqjtu.model.BranchDoctorPosition;
import com.cqjtu.model.BranchDoctorTitle;
import com.cqjtu.service.BranchDoctorPositionService;
import com.cqjtu.service.BranchDoctorTtitleService;
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
@RequestMapping("/branch-doctor-title/")
public class BranchDoctorTtitleController {


    @Value("${HospitalAdmin.code}")
    private String adminCode;


    @Value("${pageInfo.pageSize}")
    private String pageSizeString;

    @Value("${pageInfo.navigatePages}")
    private String navigatePagesString;



    @Autowired
    private BranchDoctorTtitleService branchDoctorTtitleService;


    @RequestMapping(value = {"","/"},method = RequestMethod.POST)
    public Message addBranchDoctorPosition(String token,
                                           HttpServletRequest request ,
                                           BranchDoctorTitle branchDoctorTitle){
        Message message = new Message();
        if (token == null){
            token = request.getHeader("token");
        }
        validateAndOpt(token,message,RequestMethod.POST,branchDoctorTitle);
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
        validateAndOpt(token,message,RequestMethod.PUT,branchDoctorTitle);
        return  message;
    }


    private void validateAndOpt(String token ,Message message ,RequestMethod method,BranchDoctorTitle branchDoctorTitle){
        if (token == null || token.length() <=0){
            //TODO  完成角色权限认证
            message.setInfo("未授权");
        }else {
            boolean flag = true;
            if (method.equals(RequestMethod.POST)){
                //添加操作
                if (branchDoctorTitle.getBranchId() == null || branchDoctorTitle.getBranchId() <=0){
                    flag = false;
                }
                if (branchDoctorTitle.getTitleId() == null || branchDoctorTitle.getTitleId() <=0){
                    flag = false;
                }
                if (branchDoctorTitle.getIdCard() == null || branchDoctorTitle.getIdCard().length() <=0){
                    flag = false;
                }
                if (flag){
                    int add = branchDoctorTtitleService.addBranchDoctorTitile(branchDoctorTitle);
                    if (add >0 ){
                        message.setCode(200);
                        message.setInfo("添加科室医生职称信息成功");
                        branchDoctorTitle.setBdtId(add);
                    }else {
                        message.setInfo("添加科室医生职称信息失败");
                    }
                }
            }else if (method.equals(RequestMethod.PUT)){
                //修改操作
                if (branchDoctorTitle.getBdtId() == null||branchDoctorTitle.getBdtId() <=0 ){
                    flag =false;
                }
                if (branchDoctorTitle.getBranchId() == null || branchDoctorTitle.getBranchId() <=0){
                    flag = false;
                }
                if (branchDoctorTitle.getTitleId() == null || branchDoctorTitle.getTitleId() <=0){
                    flag = false;
                }
                if (branchDoctorTitle.getIdCard() == null || branchDoctorTitle.getIdCard().length() <=0){
                    flag = false;
                }
                if (flag){
                    int update = branchDoctorTtitleService.updateBranchDoctorTitle(branchDoctorTitle);
                    if (update == 1 ){
                        message.setCode(200);
                        message.setInfo("修改科室医生职称信息成功");
                    }else {
                        message.setInfo("修改科室医生职称信息失败");
                    }
                }
            }
            if (!flag){
                message.setInfo("参数错误");
            }
            message.put("branchDoctorTitle",branchDoctorTitle);
        }
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
            //todo 为token做用户权限认证
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
            List<BranchDoctorTitle> branchDoctorTitles = branchDoctorTtitleService.queryBranchDoctorTitle(branchDoctorTitle);
            PageInfo pageInfo = new PageInfo(branchDoctorTitles,navigatePages);
            message.setCode(200);
            message.setInfo("获取科室医生职称信息成功");
            message.put("pageInfo",pageInfo);
        }
    }







}
