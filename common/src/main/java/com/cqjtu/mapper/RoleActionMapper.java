package com.cqjtu.mapper;

import com.cqjtu.model.RoleAction;
import com.cqjtu.model.RoleActionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleActionMapper {
    long countByExample(RoleActionExample example);

    int deleteByExample(RoleActionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RoleAction record);

    int insertSelective(RoleAction record);

    List<RoleAction> selectByExample(RoleActionExample example);

    RoleAction selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RoleAction record, @Param("example") RoleActionExample example);

    int updateByExample(@Param("record") RoleAction record, @Param("example") RoleActionExample example);

    int updateByPrimaryKeySelective(RoleAction record);

    int updateByPrimaryKey(RoleAction record);
}