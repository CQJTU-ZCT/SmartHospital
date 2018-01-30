package com.cqjtu.mapperexp;

import com.cqjtu.model.EmrRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author mevur
 * @date 18/1/30
 **/
public interface EmrRecordMapperExp {
    List<EmrRecord> get(Integer page, Integer limit);

    Integer count();

    EmrRecord getById(@Param("id") String id);

    int insert(EmrRecord emrRecord);

    int delete(@Param("id") String id);

    int update(EmrRecord emrRecord);
}
