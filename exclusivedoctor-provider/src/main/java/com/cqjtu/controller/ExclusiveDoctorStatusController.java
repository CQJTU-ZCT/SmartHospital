package com.cqjtu.controller;

import com.cqjtu.messages.Message;
import com.cqjtu.model.ExclusiveDoctorStatus;
import com.cqjtu.service.ExclusiveDoctorService;
import com.cqjtu.service.ExclusiveDoctorStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/2/6.
 */
@RestController
@RequestMapping("/exclusive-doctor/status")
public class ExclusiveDoctorStatusController {

    @Autowired
    private ExclusiveDoctorStatusService exclusiveDoctorStatusService;

    @RequestMapping(value = {"","/"},method = RequestMethod.GET)
    public Message getStatus(String token, HttpServletRequest request){
        Message message = new Message();
        String info ="";
        if (token == null || token .length() <=0){
            token = request.getHeader("token");
        }
        if (token == null || token.length() <=0){
            info = "未授权";
            message.setCode(401);
        }else {
            List<ExclusiveDoctorStatus> status = exclusiveDoctorStatusService.getStatus();
            info = "获取专属医生状态信息成功";
            message.setCode(200);
            message.put("status",status);
        }
        message.setInfo(info);
        return message;
    }

}
