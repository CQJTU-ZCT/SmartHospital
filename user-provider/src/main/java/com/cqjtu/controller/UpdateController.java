package com.cqjtu.controller;

import com.cqjtu.messages.Message;
import com.cqjtu.model.Users;
import com.cqjtu.service.UpdateService;
import com.cqjtu.tools.LoggerTool;
import com.cqjtu.tools.RegularTool;
import com.cqjtu.tools.ValidateAdminTool;
import com.sun.org.apache.regexp.internal.RE;
import com.sun.xml.internal.bind.marshaller.MinimumEscapeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 修改用户个人信息
 * @author Tangyu
 * @date 18/4/27
 */
@RestController
@RequestMapping("/users")
public class UpdateController {

    //accountStatusId idCard roleId realname不可修改
    //phone mail password可修改

    @Value("${hospitalAdmin.code}")
    private String adminCode;

    @Value("${hospitalDoctor.code}")
    private String doctorCode;

    @Autowired
    private  UpdateService updateService;

    /**
     * 修改用户电话号码
     * @param request
     * @param phone
     * @reurn
     */
    @RequestMapping(value = {"/update/phone"} , method = RequestMethod.PUT)
    public Message updateUserPhone(String token , HttpServletRequest request, String phone) {
        Message message = new Message();
        if (token == null || token.length() <= 0){
            token = request.getHeader("token");
        }else {
            if (ValidateAdminTool.isAdmin(request, adminCode) || ValidateAdminTool.isAdmin(request, doctorCode)) {
                //管理员 医生，可修改用户的电话号码
            } else {
                //普通用户
                if (RegularTool.isPhone(phone)) {
                    try {
                        int i = updateService.updateUserPhone(phone);
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
            }
        }
        return message;
    }

    @RequestMapping(value = {"/update/users"},method = {RequestMethod.PUT})
    public Message UpdateUsers(String token, HttpServletRequest request, Users users) {
        Message message = new Message();

        Users user = (Users)request.getAttribute("user");

        if (token == null || token.length() <= 0) {
            token = request.getHeader("token");
        } else {
            if (ValidateAdminTool.isAdmin(request, adminCode) || ValidateAdminTool.isAdmin(request, doctorCode)) {
                //管理员或医生
            }else {
                //普通用户
            }
        }
        return message;

    }

}
