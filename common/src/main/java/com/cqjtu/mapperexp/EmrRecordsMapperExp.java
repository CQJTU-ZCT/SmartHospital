package com.cqjtu.mapperexp;

import com.cqjtu.modelexp.EmrRecordExp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmrRecordsMapperExp {
    List<EmrRecordExp> getEmrRecords(@Param("emrId") String emrId);
}
