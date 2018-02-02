package com.cqjtu.controller;

import com.cqjtu.domain.PageInfo;
import com.cqjtu.messages.Message;
import com.cqjtu.service.PayRecordServiceImpl;
import com.cqjtu.tools.PageHandler;
import com.cqjtu.tools.PagesHelper;
import com.github.pagehelper.PageHelper;
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
        PageHandler.handlePage(page, limit);
        Message msg = new Message(200, "");
        msg.put("pages", this.page(limit).getMap().get("pages"));
        msg.put("pay_record", service.get(null, page, limit));
        return msg;
    }

    @RequestMapping(value = "/pay-record/page", method = RequestMethod.GET)
    public Message page(Integer limit) {
        Message msg = new Message(200, "");
        if (null == this.pageInfo || this.pageInfo.needToReget(limit)) {
            if (null == limit) {
                limit = 20;
            }
            this.pageInfo = PagesHelper.getPageInfo("pay_record",
                    limit, service, null);
        }
        msg.put("pages", this.pageInfo);
        return msg;
    }




}
