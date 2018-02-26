package com.cqjtu.controller;

import com.cqjtu.domain.PageInfo;
import com.cqjtu.messages.Message;
import com.cqjtu.model.PayRecord;
import com.cqjtu.service.PayRecordServiceImpl;
import com.cqjtu.tools.PageHandler;
import com.cqjtu.tools.PagesHelper;
import com.cqjtu.tools.SnowFlakeWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mevur
 * @date 18/2/1
 **/

@RestController
public class PayRecordController {

    @Autowired
    PayRecordServiceImpl service;

    private PageInfo pageInfo;

    private SnowFlakeWorker idWorker = new SnowFlakeWorker(3, 1);

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

    @RequestMapping(value = "/pay-record/{id}", method = RequestMethod.GET)
    public Message getById(@PathVariable("id") String id) {
        Message msg = new Message(200, "");
        PayRecord record = service.getById(id);
        if (null == record) {
            msg.setInfo("数据不存在");
        }
        msg.put("pay_record", record);
        return msg;
    }

    @RequestMapping(value = "/pay-record/{id}", method = RequestMethod.PUT)
    public Message update(@PathVariable("id") String id, PayRecord record) {
        Message msg = new Message(200, "");
        Map<String, Object> param = new HashMap<>();
        param.put("record_id", id);
        List<PayRecord> records = service.get(param, null, null);
        if (null == records || records.size() != 1) {
            msg.setInfo("数据更新数据不存在不存在");
            msg.put("pay_record", null);
        } else {
            record.setRecordId(id);
            PayRecord record1 = service.update(record);
            if (null == records) {
                msg.setInfo("更新错误");
            }
            msg.put("pay_record", record1);
        }
        return msg;
    }

    @RequestMapping(value = "/pay-record/", method = RequestMethod.POST)
    public Message insert(PayRecord record) {
        Message message = new Message(200, "");
        record.setRecordId(String.valueOf(idWorker.nextId()));
        PayRecord record1 = service.insert(record);
        if (null == record1) {
            message.setInfo("插入失败");
        }
        message.put("pay_record", record1);
        return message;
    }
}
