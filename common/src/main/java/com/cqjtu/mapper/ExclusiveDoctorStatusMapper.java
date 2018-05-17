package com.cqjtu.mapper;

import com.cqjtu.model.ExclusiveDoctorStatus;
import com.cqjtu.model.ExclusiveDoctorStatusExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExclusiveDoctorStatusMapper {
    long countByExample(ExclusiveDoctorStatusExample example);

    int deleteByExample(ExclusiveDoctorStatusExample example);

    int deleteByPrimaryKey(Integer statusId);

    int insert(ExclusiveDoctorStatus record);

    int insertSelective(ExclusiveDoctorStatus record);

    List<ExclusiveDoctorStatus> selectByExample(ExclusiveDoctorStatusExample example);

    ExclusiveDoctorStatus selectByPrimaryKey(Integer statusId);

    int updateByExampleSelective(@Param("record") ExclusiveDoctorStatus record, @Param("example") ExclusiveDoctorStatusExample example);

    int updateByExample(@Param("record") ExclusiveDoctorStatus record, @Param("example") ExclusiveDoctorStatusExample example);

    int updateByPrimaryKeySelective(ExclusiveDoctorStatus record);

    int updateByPrimaryKey(ExclusiveDoctorStatus record);
}