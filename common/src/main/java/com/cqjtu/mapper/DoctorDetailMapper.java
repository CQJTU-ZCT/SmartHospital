package com.cqjtu.mapper;

import com.cqjtu.model.DoctorDetail;
import com.cqjtu.model.DoctorDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DoctorDetailMapper {
    long countByExample(DoctorDetailExample example);

    int deleteByExample(DoctorDetailExample example);

    int deleteByPrimaryKey(String idCard);

    int insert(DoctorDetail record);

    int insertSelective(DoctorDetail record);

    List<DoctorDetail> selectByExample(DoctorDetailExample example);

    DoctorDetail selectByPrimaryKey(String idCard);

    int updateByExampleSelective(@Param("record") DoctorDetail record, @Param("example") DoctorDetailExample example);

    int updateByExample(@Param("record") DoctorDetail record, @Param("example") DoctorDetailExample example);

    int updateByPrimaryKeySelective(DoctorDetail record);

    int updateByPrimaryKey(DoctorDetail record);
}