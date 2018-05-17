package com.cqjtu.mapper;

import com.cqjtu.model.AccountStatus;
import com.cqjtu.model.AccountStatusExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccountStatusMapper {
    long countByExample(AccountStatusExample example);

    int deleteByExample(AccountStatusExample example);

    int deleteByPrimaryKey(Integer accountStatusId);

    int insert(AccountStatus record);

    int insertSelective(AccountStatus record);

    List<AccountStatus> selectByExample(AccountStatusExample example);

    AccountStatus selectByPrimaryKey(Integer accountStatusId);

    int updateByExampleSelective(@Param("record") AccountStatus record, @Param("example") AccountStatusExample example);

    int updateByExample(@Param("record") AccountStatus record, @Param("example") AccountStatusExample example);

    int updateByPrimaryKeySelective(AccountStatus record);

    int updateByPrimaryKey(AccountStatus record);
}