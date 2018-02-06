package com.cqjtu.controller;

import com.cqjtu.messages.Message;
import com.cqjtu.model.ExclusiveDoctor;
import com.cqjtu.model.Feedback;
import com.cqjtu.modelexp.ExclusiveDoctorExp;
import com.cqjtu.service.ExclusiveDoctorService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/2/6.
 */
@RestController
@RequestMapping("/exclusive-doctor")
public class ExclusiveDoctorController {


    @Autowired
    private ExclusiveDoctorService exclusiveDoctorService;


    @Value("${pageInfo.pageSize}")
    private String pageSizeString;

    @Value("${pageInfo.navigatePages}")
    private String navigatePagesString;

    @RequestMapping(value = {"","/"},method = RequestMethod.POST)
    public Message addExclusiveDoctor(String token , HttpServletRequest request , ExclusiveDoctor exclusiveDoctor){
        Message message = new Message();
        if (token == null || token.length() <=0){
            token = request.getHeader("token") ;
        }
        validateAndOpt(token,message,RequestMethod.POST,exclusiveDoctor);
        return  message;
    }

    private void validateAndOpt(String token, Message message, RequestMethod method, ExclusiveDoctor exclusiveDoctor) {
        String info = "";
        if (token == null || token.length() <=0){
            message.setCode(403);
            info = "未授权";
        }else {
            boolean flag = true;
            if (method.equals(RequestMethod.POST)){
                if (exclusiveDoctor.getUserIdCard() == null || exclusiveDoctor.getUserIdCard().length() <=0){
                    info = "用户身份证号码不能为空";
                    flag = false;
                }
                if (flag){
                    if (exclusiveDoctor.getDoctorIdCard() == null || exclusiveDoctor.getDoctorIdCard().length() <=0){
                        info = "医生身份证号码不能为空";
                        flag = false;
                    }
                }
                if (flag){
                    ExclusiveDoctor selectExclusiveDoctor = new ExclusiveDoctor();
                    selectExclusiveDoctor.setDoctorIdCard(exclusiveDoctor.getDoctorIdCard());
                    selectExclusiveDoctor.setUserIdCard(exclusiveDoctor.getUserIdCard());
                    List<ExclusiveDoctorExp> exclusiveDoctorExps = exclusiveDoctorService.queryExclusiveDoctor(selectExclusiveDoctor);
                    if (exclusiveDoctorExps != null && exclusiveDoctorExps.size() >0){
                        Integer statusId = exclusiveDoctorExps.get(0).getStatus().getStatusId();
                        if (statusId == 1){
                            info = "已经申请该医生作为专属医生，等待该医生接受，请勿重复申请";
                            flag = false;
                        }else if (statusId == 4){
                            info = "该医生已经是你的专属医生，请勿重复添加";
                            flag =false;
                        }
                        if (!flag){
                            exclusiveDoctor.setStatusId(statusId);
                            exclusiveDoctor.setExclusiveDoctorId(exclusiveDoctorExps.get(0).getExclusiveDoctorId());
                        }
                    }
                    if (flag){
                        try {
                            exclusiveDoctor.setExclusiveDoctorId(UUID.randomUUID().toString().replaceAll("-",""));
                            //1代表刚申请
                            exclusiveDoctor.setStatusId(1);
                            if (exclusiveDoctorService.addExclusiveDoctor(exclusiveDoctor) ==1){
                                message.setCode(200);
                                info = "申请专属医生成功，等待医生接受";
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                            info = "申请专属医生失败，请检查医生身份证号码和用户身份证号码是否正确";
                        }
                    }
                }
            }else if (method.equals(RequestMethod.PUT)){
                if (exclusiveDoctor.getExclusiveDoctorId() == null || exclusiveDoctor.getExclusiveDoctorId().length() <=0){
                    info = "专属医生编号不能为空";
                    flag =false;
                }
               if (flag){
                   if (exclusiveDoctor.getStatusId()== null || exclusiveDoctor.getStatusId() <=0){
                       info = "专属医生只允许修改状态，状态编号不能为空";
                       flag = false;
                   }
               }
                if (flag){
                    try {
                        if (exclusiveDoctorService.updateExclusiveDoctor(exclusiveDoctor) ==1){
                            message.setCode(200);
                            info = "修改专属医生成功";
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                        info = "修改专属医生失败，请检查状态是否存在";
                    }
                }
            }
        }
        message.setInfo(info);
        message.put("exclusiveDoctor",exclusiveDoctor);
    }

    @RequestMapping(value = {"","/"},method = RequestMethod.PUT)
    public Message updateFeedback(String token , HttpServletRequest request , ExclusiveDoctor exclusiveDoctor){
        Message message = new Message();
        if (token == null || token.length() <=0){
            token = request.getHeader("token") ;
        }
        validateAndOpt(token,message,RequestMethod.PUT,exclusiveDoctor);
        return  message;
    }

    @RequestMapping(value = {"","/"},method = RequestMethod.GET)
    public Message queryFeedback(String token , HttpServletRequest request , String pageNum,
                                 ExclusiveDoctor exclusiveDoctor){
        Message message = new Message();
        if (token == null || token.length() <=0){
            token = request.getHeader("token") ;
        }
        validateAndGet(token,pageNum,message,exclusiveDoctor);
        return  message;
    }

    private void validateAndGet(String token, String pn, Message message, ExclusiveDoctor exclusiveDoctor) {
        if (token == null){
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
            List<ExclusiveDoctorExp> exclusiveDoctorExps = exclusiveDoctorService.queryExclusiveDoctor(exclusiveDoctor);
            PageInfo pageInfo = new PageInfo(exclusiveDoctorExps,navigatePages);;
            message.setCode(200);
            message.setInfo("获取专属医生信息成功");
            message.put("pageInfo",pageInfo);
        }
    }


}
