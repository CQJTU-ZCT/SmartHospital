package com.cqjtu.mapper;

import com.cqjtu.model.UsersDetail;
import com.cqjtu.model.UsersDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UsersDetailMapper {
    long countByExample(UsersDetailExample example);

    int deleteByExample(UsersDetailExample example);

    int deleteByPrimaryKey(String idCard);

    int insert(UsersDetail record);

    int insertSelective(UsersDetail record);

    List<UsersDetail> selectByExample(UsersDetailExample example);

    UsersDetail selectByPrimaryKey(String idCard);

    int updateByExampleSelective(@Param("record") UsersDetail record, @Param("example") UsersDetailExample example);

    int updateByExample(@Param("record") UsersDetail record, @Param("example") UsersDetailExample example);

    int updateByPrimaryKeySelective(UsersDetail record);

    int updateByPrimaryKey(UsersDetail record);
}