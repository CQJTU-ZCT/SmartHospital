package com.cqjtu.mapperexp;

import com.cqjtu.model.Emr;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmrMapperExp {

    List<Emr> getEmr(@Param("page") Integer page, @Param("limit") Integer limit);

    Integer count(@Param("param")Map<String, Object> param);

    int insert(Emr emr);

    int update(Emr emr);

    Emr selectById(@Param("emrId") String emrId);

    int delete(@Param("emrId") String emrId);




}
