package com.cqjtu.controller;

import com.cqjtu.messages.Message;
import com.cqjtu.model.Branch;
import com.cqjtu.model.Position;
import com.cqjtu.service.PositionService;
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
 * @Desc 职位信息控制器
 * @date 2018/1/25.
 */
@RestController
@RequestMapping("/hospital/position")
public class PositionController {

    @Value("${hospitalAdmin.code}")
    private String adminCode;


    @Value("${pageInfo.pageSize}")
    private String pageSizeString;

    @Value("${pageInfo.navigatePages}")
    private String navigatePagesString;


    @Autowired
    private PositionService positionService;



    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public Message getPosition(HttpServletRequest request,
                               String pageNum,
                               String name,
                               String token){
        Message message = new Message();
        if (token == null){
            token = request.getHeader("token");
        }
        if (token == null || token.length() <=0 ){
            token = (String) request.getAttribute("token");
        }
        validateAndGet(message,token,name,pageNum);
        return  message;
    }


    @RequestMapping(value = {"/",""},method = RequestMethod.POST)
    public Message addPosition(HttpServletRequest request,
                               Position position,
                               String token){

        Message message = new Message();
        if (token == null){
            token = request.getHeader("token");
        }
        if (token == null || token.length() <=0 ){
            token = (String) request.getAttribute("token");
        }
        if (ValidateAdminTool.isAdmin(request,adminCode)){
            validateAndOpt(message,token,position,RequestMethod.POST);
        }else {
            message.setCode(403);
            message.setInfo("非管理员");
        }
        return message;
    }

    @RequestMapping(value = {"/",""},method = RequestMethod.PUT)
    public Message updatePosition(HttpServletRequest request,
                               Position position,
                               String token){

        Message message = new Message();
        if (token == null){
            token = request.getHeader("token");
        }
        if (token == null || token.length() <=0 ){
            token = (String) request.getAttribute("token");
        }
        if (ValidateAdminTool.isAdmin(request,adminCode)){
            validateAndOpt(message,token,position,RequestMethod.PUT);
        }else {
            message.setCode(403);
            message.setInfo("非管理员");
        }
        return message;
    }


    private void validateAndGet(Message message, String token, String name,String pn){
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
            List<Position> positions = positionService.queryPosition(name);
            PageInfo pageInfo = new PageInfo(positions,navigatePages);
            message.setCode(200);
            message.setInfo("获取医院职位信息成功");
            message.put("pageInfo",pageInfo);
        }
    }

    private void validateAndOpt(Message message, String token, Position position,RequestMethod method){
        if (token == null || token.length() <=0){
            message.setInfo("未授权");
            message.setCode(403);
        }else {
            boolean flag = true;
            if(method.equals(RequestMethod.POST)){
                //执行添加
                if (position.getName()== null || position.getName().length() <=0){
                    flag = false;
                }
                if (flag){
                    //position.setPositionId(Short.parseShort("1"));
                    int addPosition = positionService.addPosition(position);
                    if (addPosition > 0){
                        message.setCode(200);
                        message.setInfo("添加职位信息成功");
                        position.setPositionId(addPosition);
                    }else {
                        message.setInfo("添加职位信息失败");
                    }
                }
            }else if (method.equals(RequestMethod.PUT)){
                //执行修改操作
                if (position.getPositionId() <=0){
                    flag = false;
                }
                if (position.getName()== null || position.getName().length() <=0){
                    flag = false;
                }
                if (flag){
                    int updatePosition = positionService.updatePosition(position);
                    if (updatePosition == 1){
                        message.setCode(200);
                        message.setInfo("修改职位信息成功");
                    }else {
                        message.setInfo("添加职位信息失败");
                    }
                }
            }

            if (!flag){
                message.setInfo("参数错误");
            }
            message.put("position",position);
        }
    }

}
