package com.cqjtu.mapper;

import com.cqjtu.model.Profile;
import com.cqjtu.model.ProfileExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProfileMapper {
    long countByExample(ProfileExample example);

    int deleteByExample(ProfileExample example);

    int deleteByPrimaryKey(String profileId);

    int insert(Profile record);

    int insertSelective(Profile record);

    List<Profile> selectByExample(ProfileExample example);

    Profile selectByPrimaryKey(String profileId);

    int updateByExampleSelective(@Param("record") Profile record, @Param("example") ProfileExample example);

    int updateByExample(@Param("record") Profile record, @Param("example") ProfileExample example);

    int updateByPrimaryKeySelective(Profile record);

    int updateByPrimaryKey(Profile record);
}