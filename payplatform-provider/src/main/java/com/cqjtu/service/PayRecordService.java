package com.cqjtu.service;

import com.cqjtu.model.PayRecord;

import java.util.List;
import java.util.Map;

public interface PayRecordService {

    PayRecord insert(PayRecord record);

    PayRecord delete(String recordId);

    PayRecord update(PayRecord record);

    List<PayRecord> get(Map<String, Object> param, Integer page, Integer limit);

    Integer count(Map<String, Object> param);

    PayRecord getById(String recordId);
}
