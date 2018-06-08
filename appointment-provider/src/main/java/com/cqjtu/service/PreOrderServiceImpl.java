package com.cqjtu.service;

import com.cqjtu.mapper.DoctorMapper;
import com.cqjtu.mapperexp.PreordersMapperExp;
import com.cqjtu.model.Doctor;
import com.cqjtu.model.PreOrders;
import com.cqjtu.tools.LoggerTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;

/**
 * @description: ${description}
 * @author: codezhang
 * @date: 2018-06-08 10:16
 **/
@Service
public class PreOrderServiceImpl implements PreOrderService {

    @Autowired
    private PreordersMapperExp preordersMapperExp;

    @Override
    public int insert(PreOrders preOrders) {
        try {
            return preordersMapperExp.insert(preOrders);
        }catch (Exception e){
            LoggerTool.getLogger(this.getClass()).info(e.getMessage());
            return 0;
        }
    }

    @Override
    public int updateCancleTime(PreOrders preOrders) {
        try {
            return preordersMapperExp.updateCancleTime(preOrders);
        }catch (Exception e){
            LoggerTool.getLogger(this.getClass()).info(e.getMessage());
            return 0;
        }
    }

    @Override
    public int updateFinishTime(PreOrders preOrders) {
        try {
            return preordersMapperExp.updateFinishTime(preOrders);
        }catch (Exception e){
            LoggerTool.getLogger(this.getClass()).info(e.getMessage());
            return 0;
        }
    }

    @Override
    public List<PreOrders> select(PreOrders preOrders) {
        try {
            return preordersMapperExp.select(preOrders);
        }catch (Exception e){
            LoggerTool.getLogger(this.getClass()).info(e.getMessage());
            return null;
        }
    }


    @Autowired
    private DoctorMapper doctorMapper;

    @Override
    public boolean exitsDoctor(String doctorId) {
        try {
            if (doctorMapper.selectByPrimaryKey(doctorId) == null){
                return false;
            }else {
                return true;
            }
        }catch (Exception e){
            LoggerTool.getLogger(this.getClass()).info(e.getMessage());
            return false;
        }
    }
}
