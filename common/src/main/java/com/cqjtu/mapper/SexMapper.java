package com.cqjtu.mapper;

import com.cqjtu.model.Sex;
import com.cqjtu.model.SexExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SexMapper {
    long countByExample(SexExample example);

    int deleteByExample(SexExample example);

    int deleteByPrimaryKey(Integer sexId);

    int insert(Sex record);

    int insertSelective(Sex record);

    List<Sex> selectByExample(SexExample example);

    Sex selectByPrimaryKey(Integer sexId);

    int updateByExampleSelective(@Param("record") Sex record, @Param("example") SexExample example);

    int updateByExample(@Param("record") Sex record, @Param("example") SexExample example);

    int updateByPrimaryKeySelective(Sex record);

    int updateByPrimaryKey(Sex record);
}