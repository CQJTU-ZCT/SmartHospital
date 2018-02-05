package com.cqjtu.mapper;

import com.cqjtu.model.EmrRecord;
import com.cqjtu.model.EmrRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmrRecordMapper {
    long countByExample(EmrRecordExample example);

    int deleteByExample(EmrRecordExample example);

    int deleteByPrimaryKey(String recordId);

    int insert(EmrRecord record);

    int insertSelective(EmrRecord record);

    List<EmrRecord> selectByExample(EmrRecordExample example);

    EmrRecord selectByPrimaryKey(String recordId);

    int updateByExampleSelective(@Param("record") EmrRecord record, @Param("example") EmrRecordExample example);

    int updateByExample(@Param("record") EmrRecord record, @Param("example") EmrRecordExample example);

    int updateByPrimaryKeySelective(EmrRecord record);

    int updateByPrimaryKey(EmrRecord record);
}