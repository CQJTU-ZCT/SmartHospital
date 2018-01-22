package com.cqjtu.mapper;

import com.cqjtu.model.SosRecord;
import com.cqjtu.model.SosRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SosRecordMapper {
    long countByExample(SosRecordExample example);

    int deleteByExample(SosRecordExample example);

    int deleteByPrimaryKey(String sosId);

    int insert(SosRecord record);

    int insertSelective(SosRecord record);

    List<SosRecord> selectByExample(SosRecordExample example);

    SosRecord selectByPrimaryKey(String sosId);

    int updateByExampleSelective(@Param("record") SosRecord record, @Param("example") SosRecordExample example);

    int updateByExample(@Param("record") SosRecord record, @Param("example") SosRecordExample example);

    int updateByPrimaryKeySelective(SosRecord record);

    int updateByPrimaryKey(SosRecord record);
}