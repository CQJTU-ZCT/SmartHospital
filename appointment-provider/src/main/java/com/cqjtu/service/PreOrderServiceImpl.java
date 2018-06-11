package com.cqjtu.service;

import com.cqjtu.mapper.BranchDoctorTitleMapper;
import com.cqjtu.mapper.BranchMapper;
import com.cqjtu.mapper.DoctorMapper;
import com.cqjtu.mapperexp.BranchDoctorTitleMapperExp;
import com.cqjtu.mapperexp.PreordersMapperExp;
import com.cqjtu.model.Doctor;
import com.cqjtu.model.PreOrders;
import com.cqjtu.modelexp.PreorderExp;
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
    public List<PreorderExp> select(PreOrders preOrders) {
        try {
            return preordersMapperExp.select(preOrders);
        }catch (Exception e){
            LoggerTool.getLogger(this.getClass()).info(e.getMessage());
            return null;
        }
    }


    @Autowired
    private DoctorMapper doctorMapper;

    @Autowired
    private BranchDoctorTitleMapperExp branchDoctorTitleMapperExp;

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

    @Override
    public boolean exitsBranchDoctor(String branchId,String doctorId) {
        try {
            if (branchDoctorTitleMapperExp.exitsBranchDoctor(branchId,doctorId) == null){
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
