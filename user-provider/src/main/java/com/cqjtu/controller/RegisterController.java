package com.cqjtu.controller;

import com.cqjtu.messages.Message;
import com.cqjtu.model.Users;
import com.cqjtu.service.RegisterService;
import com.cqjtu.service.UserService;
import com.cqjtu.tools.Md5Tool;
import com.cqjtu.tools.RegularTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户注册控制器
 * Created by Tangyu on 2018/4/21.
 */
@RestController
@RequestMapping("/users")
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private RegisterService registerService;

    @RequestMapping(value = {"/register","/register/"},method = {RequestMethod.POST})
    public Message register(Users users){
        Message message = new Message();
        /*格式校验：
        idCard phone mail使用RegularTool校验
        password长度不少于8位
        realname不能为空
        account_stauts_id role_id设置默认值为1
        */
        if (RegularTool.isIdCard(users.getIdCard())){
            if (RegularTool.isMail(users.getMail())){
                if (RegularTool.isPhone(users.getPhone())){
                    if (users.getRealname().trim().length() > 0){
                        if (users.getPassword().trim().length() >= 8){
                            //MD5加密
                            users.setPassword(Md5Tool.stringMd5(users.getPassword()));
                            //调用userService，判断当前用户是否已经存在
                            if (userService.getUserByIdCard(users.getIdCard()) == null){
                                //当前用户不存在，可以注册
                                //设置用户状态码id、角色id 默认值 均为1
                                users.setAccountStatusId(1);
                                users.setRoleId(1);
                                //调用registerService，进行注册
                                if (registerService.addUsers(users) == 1){
                                    message.setInfo("注册成功");
                                    message.setCode(200);
                                    users.setPassword("***密码已经屏蔽***");
                                    message.put("user",users);
                                }else {
                                    message.setInfo("服务器忙，请稍后重试");
                                }
                            }else {
                                message.setInfo("该身份证号码已注册，请勿重复注册");
                            }
                        }else {
                            message.setInfo("密码长度不能小于8位");
                        }
                    }else {
                        message.setInfo("真实姓名不能为空");
                    }
                }else {
                    message.setInfo("电话号码不合法");
                }
            }else {
                message.setInfo("邮箱不合法");
            }
        }else {
            message.setInfo("身份证号码不合法");
        }
        return message;
    }
}
