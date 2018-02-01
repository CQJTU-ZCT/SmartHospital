package com.cqjtu.service;

import com.cqjtu.mapperexp.PayRecordMapperExp;
import com.cqjtu.model.PayRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mevur
 * @date 18/2/1
 **/

@Service
public class PayRecordServiceImpl implements PayRecordService, Pageable {

    @Autowired
    PayRecordMapperExp mapper;

    @Override
    public PayRecord insert(PayRecord record) {
        return mapper.insert(record) == 1 ? record : null;
    }

    @Override
    public PayRecord delete(String recordId) {
        Map<String, Object> param = new HashMap<>();
        param.put("record_Id", recordId);
        List<PayRecord> records = mapper.get(param, null, null);
        if (null == recordId || records.size() <= 0) {
            //pay record which id = record id don't exist
            return null;
        }
        return mapper.delete(recordId) == 1 ? records.get(0) : null;
    }

    @Override
    public PayRecord update(PayRecord record) {
        return mapper.update(record) == 1 ? record : null;
    }

    @Override
    public List<PayRecord> get(Map<String, Object> param, Integer page, Integer limit) {
        // start = (page - 1) * limit
        // so page should be larger than zero
        return mapper.get(param, (page - 1) * limit, limit);
    }

    @Override
    public Integer count(Map<String, Object> param) {
        return mapper.count(param);
    }
}
