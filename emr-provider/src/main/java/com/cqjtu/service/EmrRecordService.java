package com.cqjtu.service;

import com.cqjtu.model.EmrRecord;

import java.util.List;

/**
 * @author mevur
 * @date 18/1/30
 **/
public interface EmrRecordService {

    List<EmrRecord> get(Integer page, Integer limit);

    Integer count();

    EmrRecord getById(String id);

    EmrRecord insert(EmrRecord emrRecord);

    EmrRecord delete(String id);

    EmrRecord update(EmrRecord emrRecord);



}
