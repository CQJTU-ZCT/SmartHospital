package com.cqjtu.controller;

import com.cqjtu.messages.Message;
import com.cqjtu.model.DoctorDetail;
import com.cqjtu.model.Feedback;
import com.cqjtu.service.FeedbackService;
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
 * @date 2018/1/28.
 */
@RestController
@RequestMapping("/feedback")
public class FeedbackController {



    @Value("${pageInfo.pageSize}")
    private String pageSizeString;

    @Value("${pageInfo.navigatePages}")
    private String navigatePagesString;


    @Autowired
    private FeedbackService feedbackService;


    @RequestMapping(value = {"","/"},method = RequestMethod.POST)
    public Message addFeedback(String token , HttpServletRequest request , Feedback feedback){
        Message message = new Message();
        if (token == null || token.length() <=0){
            token = request.getHeader("token") ;
        }
        validateAndOpt(token,message,RequestMethod.POST,feedback);
        return  message;
    }

    @RequestMapping(value = {"","/"},method = RequestMethod.PUT)
    public Message updateFeedback(String token , HttpServletRequest request , Feedback feedback){
        Message message = new Message();
        if (token == null || token.length() <=0){
            token = request.getHeader("token") ;
        }
        validateAndOpt(token,message,RequestMethod.PUT,feedback);
        return  message;
    }

    @RequestMapping(value = {"","/"},method = RequestMethod.GET)
    public Message queryFeedback(String token , HttpServletRequest request , String pageNum,
                                 Feedback feedback){
        Message message = new Message();
        if (token == null || token.length() <=0){
            token = request.getHeader("token") ;
        }
        validateAndGet(token,pageNum,message,feedback);
        return  message;
    }


    private void validateAndGet(String token ,String pn,Message message,Feedback feedback){
        if (token == null){
            message.setInfo("未授权");
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
            List<Feedback> feedbacks = feedbackService.queryFeedback(feedback);
            PageInfo pageInfo = new PageInfo(feedbacks,navigatePages);;
            message.setCode(200);
            message.setInfo("获取反馈信息成功");
            message.put("pageInfo",pageInfo);
        }
    }


    private void validateAndOpt(String token ,Message message ,RequestMethod method ,Feedback feedback){
        if (token == null || token.length() <=0){
            message.setInfo("未授权");
        }else {
            boolean flag = true;
            if (method.equals(RequestMethod.POST)){
                if (feedback.getTitle() == null || feedback.getTitle().length() <=0){
                    flag = false;
                }
                if (feedback.getDescription() == null || feedback.getDescription().length() <=0){
                    flag =false;
                }
                if (flag){
                    feedback.setFeedbackId(UUID.randomUUID().toString().replaceAll("-",""));
                    int i = feedbackService.addFeedback(feedback);
                    if (i == 1){
                        message.setCode(200);
                        message.setInfo("添加反馈成功");
                    }else {
                        message.setInfo("添加反馈失败,请检查参数或稍后再试");
                    }
                }
            }else if (method.equals(RequestMethod.PUT)){
                if (feedback.getFeedbackId() == null || feedback.getFeedbackId().length() <=0){
                    flag = false;
                }
                if (feedback.getTitle()== null && feedback.getDescription() == null &&
                        feedback.getFeedbackStatusId() == null){
                    flag =false;
                }
                if (feedback.getTitle() != null && feedback.getTitle().length() <=0){
                    flag =false;
                }
                if (feedback.getDescription() != null && feedback.getDescription().length() <=0){
                    flag = false;
                }
                if (flag){
                    int i = feedbackService.updateFeedback(feedback);
                    if (i == 1){
                        message.setCode(200);
                        message.setInfo("修改反馈成功");
                    }else {
                        message.setInfo("修改反馈失败,请检查参数或稍后再试");
                    }
                }
            }
            if (!flag){
                message.setInfo("参数错误，请检查参数");
            }
        }
    }



}
