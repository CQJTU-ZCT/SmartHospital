package com.cqjtu.mapper;

import com.cqjtu.model.Nation;
import com.cqjtu.model.NationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NationMapper {
    long countByExample(NationExample example);

    int deleteByExample(NationExample example);

    int deleteByPrimaryKey(Integer nationId);

    int insert(Nation record);

    int insertSelective(Nation record);

    List<Nation> selectByExample(NationExample example);

    Nation selectByPrimaryKey(Integer nationId);

    int updateByExampleSelective(@Param("record") Nation record, @Param("example") NationExample example);

    int updateByExample(@Param("record") Nation record, @Param("example") NationExample example);

    int updateByPrimaryKeySelective(Nation record);

    int updateByPrimaryKey(Nation record);
}