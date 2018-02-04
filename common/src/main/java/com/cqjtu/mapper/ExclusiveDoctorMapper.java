package com.cqjtu.mapper;

import com.cqjtu.model.ExclusiveDoctor;
import com.cqjtu.model.ExclusiveDoctorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExclusiveDoctorMapper {
    long countByExample(ExclusiveDoctorExample example);

    int deleteByExample(ExclusiveDoctorExample example);

    int deleteByPrimaryKey(String exclusiveDoctorId);

    int insert(ExclusiveDoctor record);

    int insertSelective(ExclusiveDoctor record);

    List<ExclusiveDoctor> selectByExample(ExclusiveDoctorExample example);

    ExclusiveDoctor selectByPrimaryKey(String exclusiveDoctorId);

    int updateByExampleSelective(@Param("record") ExclusiveDoctor record, @Param("example") ExclusiveDoctorExample example);

    int updateByExample(@Param("record") ExclusiveDoctor record, @Param("example") ExclusiveDoctorExample example);

    int updateByPrimaryKeySelective(ExclusiveDoctor record);

    int updateByPrimaryKey(ExclusiveDoctor record);
}