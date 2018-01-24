package com.cqjtu.mapper;

import com.cqjtu.model.PayRecordDetail;
import com.cqjtu.model.PayRecordDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayRecordDetailMapper {
    long countByExample(PayRecordDetailExample example);

    int deleteByExample(PayRecordDetailExample example);

    int deleteByPrimaryKey(String payOrderId);

    int insert(PayRecordDetail record);

    int insertSelective(PayRecordDetail record);

    List<PayRecordDetail> selectByExample(PayRecordDetailExample example);

    int updateByExampleSelective(@Param("record") PayRecordDetail record, @Param("example") PayRecordDetailExample example);

    int updateByExample(@Param("record") PayRecordDetail record, @Param("example") PayRecordDetailExample example);
}