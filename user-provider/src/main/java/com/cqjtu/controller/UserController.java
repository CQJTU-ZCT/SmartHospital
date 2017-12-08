package com.cqjtu.controller;

import com.cqjtu.domain.User;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zjhfyq
 * @Desc 用户控制器
 * @date 2017/12/5.
 */


@RestController
public class UserController {


    @RequestMapping()
    public User getUserInfo(){
        System.out.println("主机被调用");
        User user = new User();
        user.setPassword("bfdkjhfkbds");
        user.setUsername("bdfgfkwhgj");
        return  user;
    }


}
