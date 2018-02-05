package com.cqjtu.mapper;

import com.cqjtu.model.OkfaStatus;
import com.cqjtu.model.OkfaStatusExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OkfaStatusMapper {
    long countByExample(OkfaStatusExample example);

    int deleteByExample(OkfaStatusExample example);

    int deleteByPrimaryKey(Integer statusId);

    int insert(OkfaStatus record);

    int insertSelective(OkfaStatus record);

    List<OkfaStatus> selectByExample(OkfaStatusExample example);

    OkfaStatus selectByPrimaryKey(Integer statusId);

    int updateByExampleSelective(@Param("record") OkfaStatus record, @Param("example") OkfaStatusExample example);

    int updateByExample(@Param("record") OkfaStatus record, @Param("example") OkfaStatusExample example);

    int updateByPrimaryKeySelective(OkfaStatus record);

    int updateByPrimaryKey(OkfaStatus record);
}