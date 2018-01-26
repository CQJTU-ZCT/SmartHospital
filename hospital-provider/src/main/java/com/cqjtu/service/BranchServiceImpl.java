package com.cqjtu.service;

import com.cqjtu.mapper.BranchMapper;
import com.cqjtu.mapperexp.BranchMapperExp;
import com.cqjtu.model.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/1/26.
 */
@Service
public class BranchServiceImpl implements  BranchService{


    @Autowired
    private BranchMapper branchMapper;


    @Autowired
    private BranchMapperExp branchMapperExp;



    @Override
    public boolean addBranch(Branch branch) {
        boolean result = false;
        int insert = branchMapper.insert(branch);
        if (insert == 1){
            result = true;
        }
        return result;
    }

    @Override
    public boolean updateBranch(Branch branch) {
        boolean result = false;
        int update =  branchMapperExp.updateBranchByPrimaryKey(branch);
        if (update == 1){
            result = true;
        }
        return result;
    }

    @Override
    public List<Branch> queryBranchesByName(String name) {
        List<Branch> branches = branchMapperExp.queryBranchesByName(name);
        return branches;
    }


    @Override
    public List<Branch> queryBranchesByIntroduction(String introduction) {
        List<Branch> branches =branchMapperExp.queryBranchesByName(introduction);
        return branches;
    }

    @Override
    public List<Branch> queryBranches(String introduction,String name) {
        return branchMapperExp.queryBranches(introduction ,name);
    }

}
