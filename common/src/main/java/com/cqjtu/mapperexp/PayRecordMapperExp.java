package com.cqjtu.mapperexp;

import com.cqjtu.model.PayRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PayRecordMapperExp {
    int insert(PayRecord record);
    int update(PayRecord record);
    int delete(@Param("recordId") String recordId);
    List<PayRecord> get(@Param("param") Map<String, Object> param,
                        @Param("page") Integer page,
                        @Param("limit") Integer limit);
    Integer count(@Param("param") Map<String, Object> param);

    PayRecord getById(@Param("recordId") String recordId);
}
