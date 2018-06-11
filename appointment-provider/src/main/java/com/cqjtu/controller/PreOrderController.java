package com.cqjtu.controller;

import com.cqjtu.messages.Message;
import com.cqjtu.model.PreOrders;
import com.cqjtu.model.Users;
import com.cqjtu.modelexp.PreorderExp;
import com.cqjtu.service.PreOrderService;
import com.cqjtu.tools.LoggerTool;
import com.cqjtu.tools.RegularTool;
import com.cqjtu.tools.Token;
import com.cqjtu.tools.TokenData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

/**
 * @description: ${description}
 * @author: codezhang
 * @date: 2018-06-08 09:46
 **/

@RestController
@RequestMapping(value = {"/preorder","/preorder/"})
public class PreOrderController {


    @Autowired
    private PreOrderService preOrderService;

    @RequestMapping(value = {"/",""},method = RequestMethod.GET)
    public Message getPreOrders(PreOrders preOrders , HttpServletRequest request, String token ){
        Message message = new Message();
        message.setCode(200);
        if (token == null || token.length() <=0){
            token = request.getHeader("token");
        }
        if (token == null || token.length() <= 0 ){
            LoggerTool.getLogger(this.getClass()).info("没有令牌信息");
            message.setCode(401);
            message.setInfo("未登录");
        }else {
            if (request.getAttribute("user") == null){
                LoggerTool.getLogger(this.getClass()).info("没有用户信息");
                message.setCode(401);
                message.setInfo("未登录");
            }else {
                Users users = null;
                try {
                    users = (Users) request.getAttribute("user");
                }catch (Exception e){
                    LoggerTool.getLogger(this.getClass()).info(e.getMessage());
                }
                if (users == null){
                    message.setCode(401);
                    message.setInfo("未登录");
                }else {
                    if (users.getRoleId() == 2){
                        preOrders.setDoctorId(users.getIdCard());
                    }else  if (users.getRoleId() == 1){
                        preOrders.setUserId(users.getIdCard());
                    }
                    List<PreorderExp> orders = preOrderService.select(preOrders);
                    message.setInfo("查询成功");
                    message.put("preorders",orders);
                }
            }
        }
        return  message;
    }

    @RequestMapping(value = {"/",""},method = RequestMethod.DELETE)
    public Message canclePreOrder(HttpServletRequest request, String token,PreOrders preOrders){
        Message message = new Message();
        message.setCode(200);
        if (token == null || token.length() <=0){
            token = request.getHeader("token");
        }
        if (token == null || token.length() <= 0 ){
            LoggerTool.getLogger(this.getClass()).info("没有令牌信息");
            message.setCode(401);
            message.setInfo("未登录");
        }else {
            if (request.getAttribute("user") == null){
                LoggerTool.getLogger(this.getClass()).info("没有用户信息");
                message.setCode(401);
                message.setInfo("未登录");
            }else {
                Users users = null;
                try {
                    users = (Users) request.getAttribute("user");
                }catch (Exception e){
                    LoggerTool.getLogger(this.getClass()).info(e.getMessage());
                }
                if (users == null){
                    message.setCode(401);
                    message.setInfo("未登录");
                }else {
                    if (preOrders.getOrderId() == null || preOrders.getOrderId().length() <=0){
                        message.setInfo("不存在的预约编号");
                    }else {
                        preOrders.setDoctorId(users.getIdCard());
                        preOrders.setUserId(users.getIdCard());
                        if ( preOrderService.updateCancleTime(preOrders) == 1){
                            message.setInfo("取消预约成功");
                        }else {
                            message.setInfo("取消预约失败，请稍后再试");
                        }
                    }
                }
            }
        }

        return  message;
    }



    @RequestMapping(value = {"/",""},method = RequestMethod.PUT)
    public Message finishPreOrder(HttpServletRequest request, String token,PreOrders preOrders){
        Message message = new Message();
        message.setCode(200);
        if (token == null || token.length() <=0){
            token = request.getHeader("token");
        }
        if (token == null || token.length() <= 0 ){
            LoggerTool.getLogger(this.getClass()).info("没有令牌信息");
            message.setCode(401);
            message.setInfo("未登录");
        }else {
            if (request.getAttribute("user") == null){
                LoggerTool.getLogger(this.getClass()).info("没有用户信息");
                message.setCode(401);
                message.setInfo("未登录");
            }else {
                Users users = null;
                try {
                    users = (Users) request.getAttribute("user");
                }catch (Exception e){
                    LoggerTool.getLogger(this.getClass()).info(e.getMessage());
                }
                if (users == null){
                    message.setCode(401);
                    message.setInfo("未登录");
                }else {
                    if (preOrders.getOrderId() == null || preOrders.getOrderId().length() <=0){
                        message.setInfo("不存在的预约编号");
                    }else {
                        if ( preOrderService.updateFinishTime(preOrders) == 1){
                            message.setInfo("完成预约成功");
                        }else {
                            message.setInfo("完成预约失败，请稍后再试");
                        }
                    }
                }
            }
        }
        return  message;
    }




    @RequestMapping(value = {"/",""},method = RequestMethod.POST)
    public Message postOrders(PreOrders preOrders , HttpServletRequest request, String token ){
        Message message = new Message();
        message.setCode(200);
        if (token == null || token.length() <=0){
            token = request.getHeader("token");
        }
        if (token == null || token.length() <= 0 ){
            LoggerTool.getLogger(this.getClass()).info("没有令牌信息");
            message.setCode(401);
            message.setInfo("未登录");
        }else {
            if (request.getAttribute("user") == null){
                LoggerTool.getLogger(this.getClass()).info("没有用户信息");
                message.setCode(401);
                message.setInfo("未登录");
            }else {
                Users users = null;
                try {
                    users = (Users) request.getAttribute("user");
                }catch (Exception e){
                    LoggerTool.getLogger(this.getClass()).info(e.getMessage());
                }
                if (users == null){
                    message.setCode(401);
                    message.setInfo("未登录");
                }else {
                    if (!RegularTool.isIdCard(preOrders.getDoctorId())){
                        message.setInfo("不存在的医生");
                    }else {
                        if (preOrders.getPreorderTime() == null || preOrders.getPreorderTime().length() <=0){
                            message.setInfo("不合法的预约时间");
                        }else {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            try {
                                Date date = sdf.parse(preOrders.getPreorderTime());
                                if (date.before(new Date())){
                                    message.setInfo("不合法的预约时间");
                                }else {
                                    if (preOrderService.exitsBranchDoctor(preOrders.getBranchId(),preOrders.getDoctorId())){
                                        preOrders.setPreorderTime(sdf.format(date));
                                        preOrders.setOrderId(Token.getToken32WithoutLine());
                                        preOrders.setAppointmentStatusId(1);
                                        preOrders.setUserId(users.getIdCard());
                                        if (preOrderService.insert(preOrders) == 1){
                                            message.setInfo("预约成功");

                                        }else {
                                            message.setInfo("预约失败,请检查预约参数");
                                        }
                                    }else {
                                        message.setInfo("该科室下没有该医生");
                                    }
                                }
                            } catch (ParseException e) {
                                LoggerTool.getLogger(this.getClass()).info(e.getMessage());
                                message.setInfo("不合法的预约时间格式，参照yyyy-MM-dd HH:mm:ss");
                            }
                        }

                    }
                }
            }
        }
        message.put("preorder",preOrders);
        return  message;
    }



}
