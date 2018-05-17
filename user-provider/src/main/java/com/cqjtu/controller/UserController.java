package com.cqjtu.controller;

import com.cqjtu.messages.Message;
import com.cqjtu.model.Users;
import com.cqjtu.modelexp.UsersExp;
import com.cqjtu.service.UserService;
import com.cqjtu.tools.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户控制器
 * Created by Tangyu on 2018/4/20.
 */

@RestController
//@RestController 是 @ResponseBody 和 @Controller 的合并
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @param token
     * @param request
     * @return
     */
    @RequestMapping(value = {"/",""},method = {RequestMethod.GET})
    public Message getUsers(String token, HttpServletRequest request, String idCard){
        //用户在登录之后可以在request中获取到当前user信息
        Message message = new Message();

        // request.setAttribute("token",token);
        // request.setAttribute("user", JsonUtil.praseJsonToBean(message.getMap().get("user").toString(),Users.class));

        //以下做一个权限认证
        if (token == null ||token.length() <=0){
            token = (String) request.getAttribute("token");
        }
        if (token == null || token.length() <= 0){
            message.setCode(401);
            message.setInfo("未授权");
        }else{
            if (idCard == null ||idCard.length() <= 0){
                message.setCode(203);
                message.setInfo("身份证号码不合法");
            }else {
                UsersExp user = userService.getUserByIdCard(idCard);
                message.put("user",user);
                message.setCode(200);
                message.setInfo("查询用户信息成功");
            }
        }
        return message;
    }
}
