package com.cqjtu.controller;

import com.cqjtu.messages.Message;
import com.cqjtu.model.Position;
import com.cqjtu.model.Title;
import com.cqjtu.service.TitleService;
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
 * @Desc 职称信息控制器
 * @date 2018/1/25.
 */
@RequestMapping("/hospital/title")
@RestController
public class TitleController {

    @Value("${hospitalAdmin.code}")
    private String adminCode;


    @Value("${pageInfo.pageSize}")
    private String pageSizeString;

    @Value("${pageInfo.navigatePages}")
    private String navigatePagesString;




    @Autowired
    private TitleService titleService;


    @RequestMapping(value = {"","/"},method = RequestMethod.POST)
    public Message addTitle(HttpServletRequest request , String token , Title title){
        Message message =  new Message();
        if (token == null || token.length() <=0){
            token = request.getHeader("token");
        }
        if (token == null || token.length() <=0 ){
            token = (String) request.getAttribute("token");
        }
        if (ValidateAdminTool.isAdmin(request,adminCode)){
            validateAndOpt(token,message,title,RequestMethod.POST);
        }else {
            message.setInfo("非管理员");
            message.setCode(403);
        }
        return message;
    }



    @RequestMapping(value = {"","/"},method = RequestMethod.PUT)
    public Message updateTitle(HttpServletRequest request , String token , Title title){
        Message message =  new Message();
        if (token == null || token.length() <=0){
            token = request.getHeader("token");
        }
        if (token == null || token.length() <=0 ){
            token = (String) request.getAttribute("token");
        }
        if (ValidateAdminTool.isAdmin(request,adminCode)){
            validateAndOpt(token,message,title,RequestMethod.PUT);
        }else {
            message.setInfo("非管理员");
            message.setCode(403);
        }
        return message;
    }

    @RequestMapping(value = {"","/"},method = RequestMethod.GET)
    public Message updateTitle(HttpServletRequest request , String token,Title title,String pageNum){
        Message message =  new Message();
        if (token == null || token.length() <=0){
            token = request.getHeader("token");
        }
        if (token == null || token.length() <=0 ){
            token = (String) request.getAttribute("token");
        }
        validateAndGet(message,token,title,pageNum);
        return message;
    }


    private void validateAndGet(Message message ,String token ,Title title,String pn){
        if (token == null ||token.length() <=0){
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
            List<Title> titles = titleService.queryTitle(title);
            PageInfo pageInfo = new PageInfo(titles,navigatePages);
            message.setCode(200);
            message.setInfo("获取医院职称信息成功");
            message.put("pageInfo",pageInfo);
        }
    }



    private void validateAndOpt(String token ,Message message,Title title,RequestMethod method){
        if (token == null || token.length() <=0){
            message.setInfo("未授权");
            message.setCode(403);
        }else {
            boolean flag = true;
            if (method.equals(RequestMethod.POST)){
                //添加操作
                if (title.getName() == null || title.getName().length() <=0){
                    flag=false;
                }
                if (flag){
                   int titleId = titleService.addTitle(title);
                   if (titleId <=0){
                       message.setInfo("添加职称信息失败");
                   }else {
                       message.setInfo("添加职称信息成功");
                   }
                }
            }else if (method.equals(RequestMethod.PUT)){
                //修改操作
                if (title.getName() == null || title.getName().length()<=0){
                    flag = false;
                }
                if (title.getTitleId()==null|| title.getTitleId() <=0){
                    flag = false;
                }
                if (flag){
                     int update = titleService.updateTitle(title);
                    if (update <=0){
                        message.setInfo("修改职称信息失败");
                    }else {
                        message.setInfo("修改职称信息成功");
                    }
                }
            }
            if (!flag){
                message.setInfo("参数错误");
            }
            message.put("title",title);
        }
    }




}
