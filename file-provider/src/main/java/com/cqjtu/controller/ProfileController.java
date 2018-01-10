package com.cqjtu.controller;

import com.cqjtu.messages.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zjhfyq
 * @Desc 头像控制器
 * @date 2018/1/10.
 */
@RestController
public class ProfileController {

    /**
     * 上传头像
     * @param request
     * @return
     */
    @RequestMapping(value = "/uploadProfile")
    public Message uploadProfile(HttpServletRequest request){

        return  null;
    }
}
