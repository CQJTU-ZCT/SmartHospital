package com.cqjtu.service;

import com.cqjtu.mapperexp.BranchDoctorTitleMapperExp;
import com.cqjtu.model.BranchDoctorPosition;
import com.cqjtu.model.BranchDoctorTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/1/26.
 */
@Service
public class BranchDoctorTitleServiceImpl implements BranchDoctorTtitleService {


    @Autowired
    private BranchDoctorTitleMapperExp branchDoctorTitleMapperExp;

    @Override
    public int addBranchDoctorTitile(BranchDoctorTitle branchDoctorTitle) {
        return branchDoctorTitleMapperExp.addBranchDoctorTitile(branchDoctorTitle);
    }

    @Override
    public int updateBranchDoctorTitle(BranchDoctorTitle branchDoctorTitle) {
        return updateBranchDoctorTitle(branchDoctorTitle);
    }

    @Override
    public List<BranchDoctorTitle> queryBranchDoctorTitle(BranchDoctorTitle branchDoctorTitle) {
        return branchDoctorTitleMapperExp.queryBranchDoctorTitle(branchDoctorTitle);
    }
}


