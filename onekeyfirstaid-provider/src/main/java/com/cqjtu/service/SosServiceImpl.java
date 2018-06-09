package com.cqjtu.service;

import com.cqjtu.mapperexp.OneKeyFirstAidMapperExp;
import com.cqjtu.model.SosRecord;
import com.cqjtu.tools.LoggerTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: ${description}
 * @author: codezhang
 * @date: 2018-06-09 11:03
 **/
@Service
public class SosServiceImpl implements SosService {

    @Autowired
    private OneKeyFirstAidMapperExp mapper;

    @Override
    public int insert(SosRecord sosRecord) {
        try{
            return mapper.insert(sosRecord);
        }catch (Exception e){
            LoggerTool.getLogger(this.getClass()).info(e.getMessage());
            return  0 ;
        }
    }

    @Override
    public int finishSos(String sosId) {
        try{
            return mapper.finishSos(sosId);
        }catch (Exception e){
            LoggerTool.getLogger(this.getClass()).info(e.getMessage());
            return  0 ;
        }
    }

    @Override
    public int cancelSos() {
        return 0;
    }

    @Override
    public List<SosRecord> select(SosRecord sosRecord) {
        try{
            return mapper.select(sosRecord);
        }catch (Exception e){
            LoggerTool.getLogger(this.getClass()).info(e.getMessage());
            return  null;
        }
    }
}
