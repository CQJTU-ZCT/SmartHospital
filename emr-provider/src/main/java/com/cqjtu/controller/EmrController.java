package com.cqjtu.controller;

import com.cqjtu.domain.PageInfo;
import com.cqjtu.messages.Message;
import com.cqjtu.model.Emr;
import com.cqjtu.service.EmrServiceImpl;
import com.cqjtu.tools.PagesHelper;
import com.cqjtu.tools.SnowFlakeWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zjhfyq
 * @Desc
 * @date 2017/12/28.
 */
@RestController
public class EmrController {

    public static final String TABLE = "emr";

    @Autowired
    EmrServiceImpl service;

    private PageInfo pageInfo;

    private SnowFlakeWorker idWorker = new SnowFlakeWorker(1, 1);

    /**
     * 添加emr数据接口
     * @param emr 添加的数据
     * @return 添加成功的数据
     */
    @RequestMapping(value = "/emr", method = RequestMethod.POST)
    public Message add(Emr emr) {
        Message message = new Message();
        message.setCode(200);
        emr.setEmrId(String.valueOf(idWorker.nextId()));
        Emr emr1 = service.insert(emr);
        if (null == emr1) {
            message.setInfo("添加emr数据失败");
        } else {
            message.put("emr", emr);
        }
        return message;
    }

    @RequestMapping(value = "/emr", method = RequestMethod.GET)
    public Message getEmrs(Integer page, Integer limit) {
        Message msg = new Message(200);
        List<Emr> emrs;
        if (null == page) {
            emrs = service.getAll();
        } else if (page > 0) {
            if (null == limit) {
                limit = 20;
            }
            page = (page - 1) * limit;
            emrs = service.emrs(page, limit);
        } else {
           emrs = service.getAll();
        }
        pageInfo = getPaperInfo(limit);
        msg.put("pages", pageInfo);
        msg.put("emrs", emrs);
        return msg;
    }

    @RequestMapping(value = "/emr/{emrId}", method = RequestMethod.DELETE)
    public Message delete(@PathVariable("emrId") String id) {
        Emr emr = service.delete(id);
        Message msg = new Message(200);
        if (null == emr) {
            msg.setInfo("删除emr数据失败");
        } else {
            msg.setInfo("删除emr数据成功");
            msg.put("emr", emr);
        }
        return msg;
    }

    @RequestMapping(value = "/emr", method = RequestMethod.PUT)
    public Message update(Emr emr) {
        Message msg = new Message(200);
        Emr emr1 = service.update(emr);
        if (null == emr1) {
            msg.setInfo("更新emr数据失败");
        } else {
            msg.setInfo("更新emr数据成功");
            msg.put("emr", emr1);
        }
        return msg;
    }


    @RequestMapping(value = "/emr/{id}", method = RequestMethod.GET)
    public Message getEmrById(@PathVariable("id") String id) {
        Message message = new Message(200);
        Emr emr = service.getEmrById(id);
        if (null == emr) {
            message.setInfo("emr不存在");
        }
        message.put("emr", emr);
        return message;
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public PageInfo getPaperInfo(Integer limit) {
        if (null != this.pageInfo && !this.pageInfo.needToReget(limit)) {
            return this.pageInfo;
        } else {
            if (null == limit) {
                limit = 20;
            }
            return PagesHelper.getPageInfo(TABLE, limit, service, null);
        }
    }
}
