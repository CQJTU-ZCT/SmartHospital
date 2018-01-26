package com.cqjtu.controller;

import com.cqjtu.messages.Message;
import com.cqjtu.model.Branch;
import com.cqjtu.model.Hospital;
import com.cqjtu.service.BranchService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.bouncycastle.cert.ocsp.Req;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.Name;
import javax.print.attribute.standard.MediaSize;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * @author zjhfyq
 * @Desc 科室信息控制器
 * @date 2018/1/25.
 */
@RestController
@RequestMapping("/hospital/{hospitalId}/branch")
public class BranchController {



    @Value("${pageInfo.pageSize}")
    private String pageSizeString;

    @Value("${pageInfo.navigatePages}")
    private String navigatePagesString;


    @Autowired
    private BranchService branchService;


    private String defaultPage ="1";


    /**
     * 操作类型标记
     */
    private String optAdd = "add";

    private String optUpdate = "update";


    @RequestMapping(value = {"/name/{name}/",
            "/name/{name}"},
            method = RequestMethod.GET)
    public Message getBranchByName(@PathVariable String hospitalId,
                                   @PathVariable String name,
                                   String pageNum ,
                             HttpServletRequest request){
        Message  message = new Message();
        String token = request.getHeader("token");
        try {
            getBranchAndValidate(hospitalId,URLDecoder.decode(name,"UTF-8"),null,pageNum,token,message);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return  message;
    }


    @RequestMapping(value = {"/",""},
            method = RequestMethod.GET)
    public Message getBranch(@PathVariable String hospitalId,
                             String pageNum,
                             HttpServletRequest request){
        Message  message = new Message();
        String token = request.getHeader("token");
        getBranchAndValidate(hospitalId,null,null,pageNum,token,message);
        return  message;
    }


    @RequestMapping(value = {"/introduction/{introduction}",
            "/introduction/{introduction}/"},
            method = RequestMethod.GET)
    public Message getBranchByIntroduction(@PathVariable String hospitalId,
                                           @PathVariable String introduction,
                                           String pageNum,
                                           HttpServletRequest request){
        Message  message = new Message();
        String token = request.getHeader("token");
        try {
            getBranchAndValidate(hospitalId, null,URLDecoder.decode(introduction,"UTF-8"),pageNum,token,message);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return  message;
    }


    @RequestMapping(value = {"/add",
            "/add/"},method = RequestMethod.PUT)
    public Message addBranch(Branch branch,HttpServletRequest request){
        Message message = new Message();
        String token = request.getHeader("token");
        checkBranchPropertiesAndOpt(token ,message ,branch ,optAdd);
        return  message;
    }


    @RequestMapping(value = {"/update",
            "/update/"},method = RequestMethod.PUT)
    public Message updateBranch(Branch branch,HttpServletRequest request){
        Message message = new Message();
        String token = request.getHeader("token");
        checkBranchPropertiesAndOpt(token ,message ,branch ,optUpdate);
        return  message;
    }



    private void checkBranchPropertiesAndOpt(String token ,Message message ,Branch branch,String optFlag){
       if (token == null || token.length() <=0){
           message.setInfo("未授权");
       }else {
           boolean flag = true;
           if (optFlag.equals(optUpdate)){
               if (branch.getBranchId() == null || branch.getBranchId() <=0){
                   flag = false;
               }
           }
           if (optFlag.equals(optAdd)){
               if (branch.getHospitalId() == null || branch.getHospitalId().length() <=0){
                   flag = false;
               }
               if (branch.getIntroduction() == null || branch.getIntroduction().length()<=0){
                   flag = false;
               }
               if (branch.getName() == null || branch.getName().length()<=0){
                   flag = false;
               }
           }

           if (flag){
               if (optFlag.equals(optAdd)){
                   if (branchService.addBranch(branch)){
                       message.setCode(100);
                       message.setInfo("添加科室成功");
                       message.put("branch",branch);
                   }else {
                       message.setInfo("添加科室失败");
                   }
               }
               if (optFlag.equals(optUpdate)){
                   if (branchService.updateBranch(branch)){
                       message.setCode(100);
                       message.setInfo("更新科室成功");
                       message.put("branch",branch);
                   }else {
                       message.setInfo("更新科室失败");
                   }
               }
           }else {
               message.setInfo("科室操作缺少参数");
           }
       }
    }













    private void getBranchAndValidate(String hospitalId, String name, String introduction,
                                      String pn , String token, Message message ){
        if (token == null || token.length() <=0){
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
            List<Branch> branches = null;
            if (name != null){
               branches =  branchService.queryBranchesByName(name, hospitalId);
            }
            if (introduction != null){
                branches = branchService.queryBranchesByIntroduction(introduction ,hospitalId);
            }
            if (name == null && introduction == null){
                branches = branchService.queryBranches(hospitalId);
            }

            PageInfo pageInfo = new PageInfo(branches,navigatePages);;
            message.setCode(200);
            message.setInfo("获取医院科室信息成功");
            message.put("pageInfo",pageInfo);
        }

    }


}
