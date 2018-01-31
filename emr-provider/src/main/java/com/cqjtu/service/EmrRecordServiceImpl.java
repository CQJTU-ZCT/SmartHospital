package com.cqjtu.service;

import com.cqjtu.mapper.EmrMapper;
import com.cqjtu.mapperexp.EmrRecordMapperExp;
import com.cqjtu.model.EmrRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author mevur
 * @date 18/1/30
 **/

@Service
public class EmrRecordServiceImpl implements EmrRecordService, Pageable {
    @Autowired
    EmrRecordMapperExp mapper;

    @Autowired
    EmrServiceImpl emrService;


    @Override
    public List<EmrRecord> get(String emrId, Integer page, Integer limit) {
        return mapper.get(emrId, page, limit);
    }

    @Override
    public Integer count(Map<String, Object> param) {
        return mapper.count(param);
    }

    @Override
    public EmrRecord getById(String id) {
        return mapper.getById(id);
    }

    @Override
    public EmrRecord insert(EmrRecord emrRecord) {
        if (emrService.getEmrById(emrRecord.getEmrId()) == null) {
            //emr不存在
            //todo verify other filed
            return null;
        }
        return mapper.insert(emrRecord) == 1 ? emrRecord : null;
    }

    @Override
    public EmrRecord delete(String id) {
        EmrRecord emrRecord = mapper.getById(id);
        if (null == emrRecord) {
            //record is not exist,
            return null;
        }
        return mapper.delete(id) == 1 ? emrRecord : null;
    }

    @Override
    public EmrRecord update(EmrRecord emrRecord) {
        if (null == emrService.getEmrById(emrRecord.getEmrId())) {
            //emr data dose'nt exist
            return null;
        }
        if (mapper.update(emrRecord) == 1) {
            return mapper.getById(emrRecord.getRecordId());
        }
        return null;
    }
}
