package com.cqjtu.controller;

import com.cqjtu.messages.Message;
import com.cqjtu.model.Users;
import com.cqjtu.service.UpdateService;
import com.cqjtu.tools.LoggerTool;
import com.cqjtu.tools.Md5Tool;
import com.cqjtu.tools.RegularTool;
import com.cqjtu.tools.ValidateAdminTool;
import com.sun.org.apache.regexp.internal.RE;
import com.sun.xml.internal.bind.marshaller.MinimumEscapeHandler;
import com.sun.xml.internal.ws.api.model.MEP;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;

/**
 * 修改用户个人信息
 *
 * @author Tangyu
 * @date 18/4/27
 */
@RestController
@RequestMapping("/users")
public class UpdateController {

    //accountStatusId idCard roleId realname不可修改
    //phone mail password可修改

    @Autowired
    private UpdateService updateService;

    /**
     * 修改用户电话号码
     * @param request
     * @param phone
     * @reurn
     */
    @RequestMapping(value = {"/phone", "/phone/", "/phone/{phone}", "/phone/{phone}/"}, method = RequestMethod.POST)
    public Message updateUserPhone(String token, HttpServletRequest request, String phone) {
        Message message = new Message();
        Users users = (Users) request.getAttribute("user");
        if (token == null || token.length() <= 0) {
            token = request.getHeader("token");
        }
        if (RegularTool.isPhone(phone)) {
            try {
                int i = updateService.updateUserPhone(users.getIdCard(), phone);
                if (i == 1) {
                    message.setCode(200);
                    message.setInfo("电话号码修改成功");
                } else {
                    message.setInfo("电话号码修改失败");
                }
            } catch (Exception e) {
                LoggerTool.getLogger(this.getClass()).info(e.getMessage());
                message.setCode(500);
            }
        } else {
            message.setCode(201);
            message.setInfo("电话号码格式有误");
        }
        return message;
    }

    /**
     * 修改用户邮箱
     * @param token
     * @param request
     * @param mail
     * @return
     */
    @RequestMapping(value = {"/mail", "/mail/", "/mail/{mail}", "/mail/{mail}"}, method = RequestMethod.POST)
    public Message updateUserMail(String token , HttpServletRequest request ,String mail){
        Message message = new Message();
        Users users = (Users)request.getAttribute("user");
        if (token == null || token.length() <=0){
            token = request.getHeader("token");
        }
        if (RegularTool.isMail(mail)){
            try {
                int i = updateService.updateUserMail(users.getIdCard(),mail);
                if (i==1){
                    message.setCode(200);
                    message.setInfo("邮箱修改成功");
                }else {
                    message.setInfo("邮箱修改失败");
                }
            }catch (Exception e){
                message.setCode(500);
                message.setInfo(e.getMessage());
            }
        }else {
            message.setCode(201);
            message.setInfo("邮箱格式错误");
        }
        return message;
    }

    @RequestMapping(value = {"/password", "/password/", "/password/{password}", "/password/{password}/"}, method = RequestMethod.POST)
    public Message updateUserPassword(String token , HttpServletRequest request , String  password){
        Message message = new Message();
        Users users = (Users)request.getAttribute("user");
        if (token == null || token.length()<=0){
            token = request.getHeader("token");
        }
        if (password.trim().length() >= 8){
            try {
                //更新密码并进行MD5加密
                int i = updateService.updateUserPassword(users.getIdCard(),Md5Tool.stringMd5(password));
                if (i == 1){
                    message.setCode(200);
                    message.setInfo("密码修改成功");
                    message.put("user",users);
                }else {
                    message.setInfo("密码修改失败");
                }
            }catch (Exception e){
                message.setCode(500);
                message.setInfo(e.getMessage());
            }
        }else {
            message.setInfo("密码长度不能小于8位");
        }
        return message;
    }
}
