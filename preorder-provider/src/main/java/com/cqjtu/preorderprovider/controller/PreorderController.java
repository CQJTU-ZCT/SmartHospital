package com.cqjtu.preorderprovider.controller;

import com.cqjtu.messages.Message;
import com.cqjtu.model.PreOrders;
import com.cqjtu.preorderprovider.service.PreorderProviderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mevur
 * @date 18/3/1
 **/
@RestController
public class PreorderController {

    @Autowired
    PreorderProviderServiceImpl service;

    @RequestMapping(value = "/preoders/", method = RequestMethod.GET)
    public Message get(PreOrders preOrders, Integer page, Integer limit) {
        Message message = new Message(200, "");
        Map<String, Object> params = getParams(preOrders);
        List<PreOrders> preOrderss = service.get(params, page, limit);
        if (null == preOrderss) {
            message.setInfo("数据库中数据为空");
        }
        message.put("preorders", preOrderss);
        return message;
    }



    public Map<String, Object> getParams(PreOrders preOrders) {
        Map<String, Object> params = new HashMap<>();
        if (null != preOrders.getAppointmentStatusId()) {
            params.put("appointment_status_id", preOrders.getAppointmentStatusId());
        }
        if (null != preOrders.getCancelTime()) {
            params.put("cancel_time", preOrders.getCancelTime());
        }
        if (null != preOrders.getCreateTime()) {
            params.put("create_time", preOrders.getCreateTime());
        }
        if (null != preOrders.getFinishTime()) {
            params.put("finish_time", preOrders.getFinishTime());
        }
        if (null != preOrders.getPrice()) {
            params.put("price", preOrders.getPrice());
        }
        return params;
    }

}
