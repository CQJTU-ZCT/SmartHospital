package com.cqjtu.mapper;

import com.cqjtu.model.Branch;
import com.cqjtu.model.BranchExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BranchMapper {
    long countByExample(BranchExample example);

    int deleteByExample(BranchExample example);

    int deleteByPrimaryKey(Integer branchId);

    int insert(Branch record);

    int insertSelective(Branch record);

    List<Branch> selectByExample(BranchExample example);

    Branch selectByPrimaryKey(Integer branchId);

    int updateByExampleSelective(@Param("record") Branch record, @Param("example") BranchExample example);

    int updateByExample(@Param("record") Branch record, @Param("example") BranchExample example);

    int updateByPrimaryKeySelective(Branch record);

    int updateByPrimaryKey(Branch record);
}