package com.cqjtu.controller;

import com.cqjtu.domain.PageInfo;
import com.cqjtu.messages.Message;
import com.cqjtu.service.PayRecordServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mevur
 * @date 18/2/1
 **/

@RestController
public class PayRecordController {

    @Autowired
    PayRecordServiceImpl service;

    private PageInfo pageInfo;

    @RequestMapping(value = "/pay-record/", method = RequestMethod.GET)
    public Message get(Integer page, Integer limit) {
        if (null == page) {
            //when page is null, set page default equal to 1 and limit equal to 20
            page = 1;
            limit = 20;
        } else if (null == limit) {
            limit = 20;
        }

    }
}
