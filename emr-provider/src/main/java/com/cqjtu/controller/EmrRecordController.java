package com.cqjtu.controller;

import com.cqjtu.domain.PageInfo;
import com.cqjtu.messages.Message;
import com.cqjtu.model.EmrRecord;
import com.cqjtu.service.EmrRecordServiceImpl;
import com.cqjtu.tools.PagesHelper;
import com.cqjtu.tools.SnowFlakeWorker;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.Year;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mevur
 * @date 18/1/30
 **/

@RestController
public class EmrRecordController {

    @Autowired
    EmrRecordServiceImpl service;

    SnowFlakeWorker idWorker = new SnowFlakeWorker(2, 1);

    private PageInfo pageInfo;


    /**
     * 获取某个emr下的全部纪录
     * @param id
     * @return
     */
    @RequestMapping(value = "/emr/{emrId}/record", method = RequestMethod.GET)
    public Message getEmrRecord(@PathVariable("emrId") String id, Integer page, Integer limit) {
        if (null == page) {
            page = 1;
            limit = 20;
        } else if (limit == null) {
            limit = 20;
        }
        List<EmrRecord> records = service.get(id, page, limit);
        Message msg = new Message(200, "");
        if (null == records) {
            msg.setInfo("数据不存在");
        }
        msg.put("pages", pages(id, limit).getMap().get("pages"));
        msg.put("emr_records", records);
        return msg;
    }

    @RequestMapping(value = "/emr/{emrId}/page", method = RequestMethod.GET)
    public Message pages(@PathVariable("emrId") String emrId, Integer limit) {
        Message msg = new Message(200, "");
        if (null != this.pageInfo && !this.pageInfo.needToReget(limit)) {
            msg.put("pages", this.pageInfo);
            return msg;
        } else {
            Map<String, Object> param = new HashMap<>();
            param.put("emrId", emrId);
            if (null == limit) {
                limit = 20;
            }
            this.pageInfo = PagesHelper.getPageInfo("emr_record",
                    limit, service, param);
            msg.put("pages", this.pageInfo);
            return msg;
        }
    }

    @RequestMapping(value = "/emr/{emrId}/record", method = RequestMethod.POST)
    public Message add(@PathVariable("emrId") String emrId, EmrRecord emrRecord) {
        emrRecord.setEmrId(emrId);
        emrRecord.setRecordId(String.valueOf(idWorker.nextId()));
        EmrRecord record = service.insert(emrRecord);
        Message msg = new Message(200, "");
        if (null == record) {
            msg.setInfo("插入病例纪录失败");
        }
        msg.put("emr_record", record);
        return msg;
    }

    @RequestMapping(value = "/emr/record/{recordId}", method = RequestMethod.GET)
    public Message getEmrRecordById(@PathVariable("recordId") String recordId) {
        Message msg = new Message(200, "");
        EmrRecord record = service.getById(recordId);
        if (null == record) {
            msg.setInfo("记录不存在");
        }
        msg.put("emr_record", record);
        return msg;
    }

    @RequestMapping(value = "/emr/record/{recordId}", method = RequestMethod.DELETE)
    public Message delete(@PathVariable("recordId") String recordId) {
        EmrRecord record = service.getById(recordId);
        Message msg = new Message(200, "");
        if (null == record) {
            msg.setInfo("记录不存在");
        }
        record = service.delete(recordId);
        if (null == record) {
            msg.setInfo("删除失败");
        }
        msg.put("emr_record", record);
        return msg;
    }

    @RequestMapping(value = "/emr/record/{recordId}", method = RequestMethod.PUT)
    public Message update(@PathVariable("recordId") String recordId, EmrRecord record) {
        Message msg = new Message(200, "");
        record.setRecordId(recordId);
        System.out.println(record.toString());
        EmrRecord rec = service.update(record);
        if (null == rec) {
            msg.setInfo("更新数据失败");
        }
        msg.put("emr_record", rec);
        return msg;
    }

}
