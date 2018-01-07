package com.cqjtu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/1/7.
 */
@RestController
public class TestController {

    @RequestMapping("/")
    public String test(){
        return new Date().toString();
    }
}
