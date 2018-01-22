package com.cqjtu.mapper;

import com.cqjtu.model.PreorderStatus;
import com.cqjtu.model.PreorderStatusExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PreorderStatusMapper {
    long countByExample(PreorderStatusExample example);

    int deleteByExample(PreorderStatusExample example);

    int deleteByPrimaryKey(Short id);

    int insert(PreorderStatus record);

    int insertSelective(PreorderStatus record);

    List<PreorderStatus> selectByExample(PreorderStatusExample example);

    PreorderStatus selectByPrimaryKey(Short id);

    int updateByExampleSelective(@Param("record") PreorderStatus record, @Param("example") PreorderStatusExample example);

    int updateByExample(@Param("record") PreorderStatus record, @Param("example") PreorderStatusExample example);

    int updateByPrimaryKeySelective(PreorderStatus record);

    int updateByPrimaryKey(PreorderStatus record);
}