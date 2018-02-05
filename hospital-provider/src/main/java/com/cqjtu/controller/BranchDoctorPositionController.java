package com.cqjtu.controller;

import com.cqjtu.mapperexp.BranchDoctorPositionMapperExp;
import com.cqjtu.messages.Message;
import com.cqjtu.model.BranchDoctorPosition;
import com.cqjtu.model.Title;
import com.cqjtu.service.BranchDoctorPositionService;
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
@RequestMapping("/branch-doctor-position/")
public class BranchDoctorPositionController {


    @Value("${HospitalAdmin.code}")
    private String adminCode;


    @Value("${pageInfo.pageSize}")
    private String pageSizeString;

    @Value("${pageInfo.navigatePages}")
    private String navigatePagesString;



    @Autowired
    private BranchDoctorPositionService branchDoctorPositionService;


    @RequestMapping(value = {"","/"},method = RequestMethod.POST)
    public Message addBranchDoctorPosition(String token,
                                           HttpServletRequest request ,
                                           BranchDoctorPosition branchDoctorPosition){
        Message message = new Message();
        if (token == null){
            token = request.getHeader("token");
        }
        validateAndOpt(token,message,RequestMethod.POST,branchDoctorPosition);
        return  message;
    }




    @RequestMapping(value = {"","/"},method = RequestMethod.PUT)
    public Message updateBranchDoctorPosition(String token,
                                           HttpServletRequest request ,
                                           BranchDoctorPosition branchDoctorPosition){
        Message message = new Message();
        if (token == null){
            token = request.getHeader("token");
        }
        validateAndOpt(token,message,RequestMethod.PUT,branchDoctorPosition);
        return  message;
    }


    private void validateAndOpt(String token ,Message message ,RequestMethod method,BranchDoctorPosition branchDoctorPosition){
        if (token == null || token.length() <=0){
            //TODO  完成角色权限认证
            message.setInfo("未授权");
        }else {
            boolean flag = true;
            if (method.equals(RequestMethod.POST)){
                //添加操作
                if (branchDoctorPosition.getBranchId() == null || branchDoctorPosition.getBranchId() <=0){
                    flag = false;
                }
                if (branchDoctorPosition.getPositionId() == null || branchDoctorPosition.getPositionId() <=0){
                    flag = false;
                }
                if (branchDoctorPosition.getIdCard() == null || branchDoctorPosition.getIdCard().length() <=0){
                    flag = false;
                }
                if (flag){
                    int add = branchDoctorPositionService.addBranchDoctorPosition(branchDoctorPosition);
                    if (add >0 ){
                        message.setCode(200);
                        message.setInfo("添加科室医生职位信息成功");
                        branchDoctorPosition.setBdpId(add);
                    }else {
                        message.setInfo("添加科室医生职位信息失败");
                    }
                }
            }else if (method.equals(RequestMethod.PUT)){
                //修改操作
                if (branchDoctorPosition.getBdpId() == null||branchDoctorPosition.getBdpId() <=0 ){
                    flag =false;
                }
                if (branchDoctorPosition.getBranchId() == null || branchDoctorPosition.getBranchId() <=0){
                    flag = false;
                }
                if (branchDoctorPosition.getPositionId() == null || branchDoctorPosition.getPositionId() <=0){
                    flag = false;
                }
                if (branchDoctorPosition.getIdCard() == null || branchDoctorPosition.getIdCard().length() <=0){
                    flag = false;
                }
                if (flag){
                    int update = branchDoctorPositionService.addBranchDoctorPosition(branchDoctorPosition);
                    if (update == 1 ){
                        message.setCode(200);
                        message.setInfo("修改科室医生职位信息成功");
                    }else {
                        message.setInfo("修改科室医生职位信息失败");
                    }
                }
            }
            if (!flag){
                message.setInfo("参数错误");
            }
            message.put("branchDoctorPosition",branchDoctorPosition);
        }
    }


    @RequestMapping(value = {"","/"},method = RequestMethod.GET)
    public Message getBranchDoctorPosition(String token,BranchDoctorPosition branchDoctorPosition,
                                           HttpServletRequest request,String pageNum){
        Message message =new Message();
        if (token == null || token.length() <=0){
            token = request.getHeader("token");
        }
        validateAndGet(message,token,branchDoctorPosition,pageNum);
        return  message;
    }


    private void validateAndGet(Message message ,String token ,BranchDoctorPosition branchDoctorPosition ,String pn){
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
            List<BranchDoctorPosition> branchDoctorPositions = branchDoctorPositionService.queryBranchDoctorPosition(branchDoctorPosition);
            PageInfo pageInfo = new PageInfo(branchDoctorPositions,navigatePages);
            message.setCode(200);
            message.setInfo("获取科室医生职位信息成功");
            message.put("pageInfo",pageInfo);
        }
    }







}
