package com.cqjtu.mapperexp;

import com.cqjtu.model.EmrRecord;
import com.cqjtu.modelexp.EmrRecordExp;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author mevur
 * @date 18/1/30
 **/
public interface EmrRecordMapperExp {
    List<EmrRecord> get(@Param("emrId") String emrId,
                        @Param("page") Integer page,
                        @Param("limit") Integer limit);

    Integer count(Map<String, Object> param);

    EmrRecord getById(@Param("id") String id);

    int insert(EmrRecord emrRecord);

    int delete(@Param("id") String id);

    int update(EmrRecord emrRecord);
}
