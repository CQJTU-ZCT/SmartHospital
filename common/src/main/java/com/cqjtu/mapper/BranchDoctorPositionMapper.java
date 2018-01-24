package com.cqjtu.mapper;

import com.cqjtu.model.BranchDoctorPosition;
import com.cqjtu.model.BranchDoctorPositionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BranchDoctorPositionMapper {
    long countByExample(BranchDoctorPositionExample example);

    int deleteByExample(BranchDoctorPositionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BranchDoctorPosition record);

    int insertSelective(BranchDoctorPosition record);

    List<BranchDoctorPosition> selectByExample(BranchDoctorPositionExample example);

    BranchDoctorPosition selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BranchDoctorPosition record, @Param("example") BranchDoctorPositionExample example);

    int updateByExample(@Param("record") BranchDoctorPosition record, @Param("example") BranchDoctorPositionExample example);

    int updateByPrimaryKeySelective(BranchDoctorPosition record);

    int updateByPrimaryKey(BranchDoctorPosition record);
}