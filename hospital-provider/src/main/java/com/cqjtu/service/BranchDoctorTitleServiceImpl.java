package com.cqjtu.service;

import com.cqjtu.mapperexp.BranchDoctorTitleMapperExp;
import com.cqjtu.model.BranchDoctorTitle;
import com.cqjtu.modelexp.BranchDoctorTitleExp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/1/26.
 */
@Service
public class BranchDoctorTitleServiceImpl implements BranchDoctorTitleService {


    @Autowired
    private BranchDoctorTitleMapperExp branchDoctorTitleMapperExp;

    @Override
    public int addBranchDoctorTitile(BranchDoctorTitle branchDoctorTitle) {
        return branchDoctorTitleMapperExp.addBranchDoctorTitle(branchDoctorTitle);
    }

    @Override
    public int updateBranchDoctorTitle(BranchDoctorTitle branchDoctorTitle) {
        return updateBranchDoctorTitle(branchDoctorTitle);
    }

    @Override
    public List<BranchDoctorTitleExp> queryBranchDoctorTitle(BranchDoctorTitle branchDoctorTitle) {
        return branchDoctorTitleMapperExp.queryBranchDoctorTitle(branchDoctorTitle);
    }
}


