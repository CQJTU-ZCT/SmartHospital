package com.cqjtu.service;

import com.cqjtu.mapperexp.BranchDoctorPositionMapperExp;
import com.cqjtu.model.Branch;
import com.cqjtu.model.BranchDoctorPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/1/26.
 */
@Service
public class BranchDoctorPositionServiceImpl implements BranchDoctorPositionService {

    @Autowired
    private BranchDoctorPositionMapperExp branchDoctorPositionMapperExp;

    @Override
    public int addBranchDoctorPosition(BranchDoctorPosition branchDoctorPosition) {
        return branchDoctorPositionMapperExp.addBranchDoctorPosition(branchDoctorPosition);
    }

    @Override
    public int updateBranchDoctorPosition(BranchDoctorPosition branchDoctorPosition) {
        return branchDoctorPositionMapperExp.updateBranchDoctorPosition(branchDoctorPosition);
    }

    @Override
    public List<BranchDoctorPosition> queryBranchDoctorPosition(BranchDoctorPosition branchDoctorPosition) {
        return branchDoctorPositionMapperExp.queryBranchDoctorPosition(branchDoctorPosition);
    }
}
