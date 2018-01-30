package com.cqjtu.mapperexp;

import com.cqjtu.model.Emr;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmrMapperExp {
    List<Emr> getEmr(@Param("start") Integer start, @Param("limit") Integer limit);
    Integer count();

}
