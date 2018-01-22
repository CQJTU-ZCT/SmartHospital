package com.cqjtu.mapper;

import com.cqjtu.model.PayStatus;
import com.cqjtu.model.PayStatusExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayStatusMapper {
    long countByExample(PayStatusExample example);

    int deleteByExample(PayStatusExample example);

    int deleteByPrimaryKey(Short id);

    int insert(PayStatus record);

    int insertSelective(PayStatus record);

    List<PayStatus> selectByExample(PayStatusExample example);

    PayStatus selectByPrimaryKey(Short id);

    int updateByExampleSelective(@Param("record") PayStatus record, @Param("example") PayStatusExample example);

    int updateByExample(@Param("record") PayStatus record, @Param("example") PayStatusExample example);

    int updateByPrimaryKeySelective(PayStatus record);

    int updateByPrimaryKey(PayStatus record);
}