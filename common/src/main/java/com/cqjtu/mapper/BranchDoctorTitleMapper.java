package com.cqjtu.mapper;

import com.cqjtu.model.BranchDoctorTitle;
import com.cqjtu.model.BranchDoctorTitleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BranchDoctorTitleMapper {
    long countByExample(BranchDoctorTitleExample example);

    int deleteByExample(BranchDoctorTitleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BranchDoctorTitle record);

    int insertSelective(BranchDoctorTitle record);

    List<BranchDoctorTitle> selectByExample(BranchDoctorTitleExample example);

    BranchDoctorTitle selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BranchDoctorTitle record, @Param("example") BranchDoctorTitleExample example);

    int updateByExample(@Param("record") BranchDoctorTitle record, @Param("example") BranchDoctorTitleExample example);

    int updateByPrimaryKeySelective(BranchDoctorTitle record);

    int updateByPrimaryKey(BranchDoctorTitle record);
}