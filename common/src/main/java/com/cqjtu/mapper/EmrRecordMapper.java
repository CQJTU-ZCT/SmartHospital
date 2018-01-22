package com.cqjtu.mapper;

import com.cqjtu.model.EmrRecord;
import com.cqjtu.model.EmrRecordExample;
import com.cqjtu.model.EmrRecordWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmrRecordMapper {
    long countByExample(EmrRecordExample example);

    int deleteByExample(EmrRecordExample example);

    int deleteByPrimaryKey(String recordId);

    int insert(EmrRecordWithBLOBs record);

    int insertSelective(EmrRecordWithBLOBs record);

    List<EmrRecordWithBLOBs> selectByExampleWithBLOBs(EmrRecordExample example);

    List<EmrRecord> selectByExample(EmrRecordExample example);

    EmrRecordWithBLOBs selectByPrimaryKey(String recordId);

    int updateByExampleSelective(@Param("record") EmrRecordWithBLOBs record, @Param("example") EmrRecordExample example);

    int updateByExampleWithBLOBs(@Param("record") EmrRecordWithBLOBs record, @Param("example") EmrRecordExample example);

    int updateByExample(@Param("record") EmrRecord record, @Param("example") EmrRecordExample example);

    int updateByPrimaryKeySelective(EmrRecordWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(EmrRecordWithBLOBs record);

    int updateByPrimaryKey(EmrRecord record);
}