package com.cqjtu.mapper;

import com.cqjtu.model.Emr;
import com.cqjtu.model.EmrExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmrMapper {
    long countByExample(EmrExample example);

    int deleteByExample(EmrExample example);

    int deleteByPrimaryKey(String emrId);

    int insert(Emr record);

    int insertSelective(Emr record);

    List<Emr> selectByExample(EmrExample example);

    Emr selectByPrimaryKey(String emrId);

    int updateByExampleSelective(@Param("record") Emr record, @Param("example") EmrExample example);

    int updateByExample(@Param("record") Emr record, @Param("example") EmrExample example);

    int updateByPrimaryKeySelective(Emr record);

    int updateByPrimaryKey(Emr record);
}