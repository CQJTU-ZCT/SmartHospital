package com.cqjtu.mapper;

import com.cqjtu.model.sex;
import com.cqjtu.model.sexExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface sexMapper {
    long countByExample(sexExample example);

    int deleteByExample(sexExample example);

    int deleteByPrimaryKey(Integer sexId);

    int insert(sex record);

    int insertSelective(sex record);

    List<sex> selectByExample(sexExample example);

    sex selectByPrimaryKey(Integer sexId);

    int updateByExampleSelective(@Param("record") sex record, @Param("example") sexExample example);

    int updateByExample(@Param("record") sex record, @Param("example") sexExample example);

    int updateByPrimaryKeySelective(sex record);

    int updateByPrimaryKey(sex record);
}