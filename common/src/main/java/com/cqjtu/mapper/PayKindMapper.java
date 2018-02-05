package com.cqjtu.mapper;

import com.cqjtu.model.PayKind;
import com.cqjtu.model.PayKindExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayKindMapper {
    long countByExample(PayKindExample example);

    int deleteByExample(PayKindExample example);

    int deleteByPrimaryKey(Integer payWayId);

    int insert(PayKind record);

    int insertSelective(PayKind record);

    List<PayKind> selectByExample(PayKindExample example);

    PayKind selectByPrimaryKey(Integer payWayId);

    int updateByExampleSelective(@Param("record") PayKind record, @Param("example") PayKindExample example);

    int updateByExample(@Param("record") PayKind record, @Param("example") PayKindExample example);

    int updateByPrimaryKeySelective(PayKind record);

    int updateByPrimaryKey(PayKind record);
}