package com.cqjtu.mapper;

import com.cqjtu.model.FileUse;
import com.cqjtu.model.FileUseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FileUseMapper {
    long countByExample(FileUseExample example);

    int deleteByExample(FileUseExample example);

    int deleteByPrimaryKey(Short id);

    int insert(FileUse record);

    int insertSelective(FileUse record);

    List<FileUse> selectByExample(FileUseExample example);

    FileUse selectByPrimaryKey(Short id);

    int updateByExampleSelective(@Param("record") FileUse record, @Param("example") FileUseExample example);

    int updateByExample(@Param("record") FileUse record, @Param("example") FileUseExample example);

    int updateByPrimaryKeySelective(FileUse record);

    int updateByPrimaryKey(FileUse record);
}