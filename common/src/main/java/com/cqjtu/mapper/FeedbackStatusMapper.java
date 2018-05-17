package com.cqjtu.mapper;

import com.cqjtu.model.FeedbackStatus;
import com.cqjtu.model.FeedbackStatusExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FeedbackStatusMapper {
    long countByExample(FeedbackStatusExample example);

    int deleteByExample(FeedbackStatusExample example);

    int deleteByPrimaryKey(Integer feedbackStatusId);

    int insert(FeedbackStatus record);

    int insertSelective(FeedbackStatus record);

    List<FeedbackStatus> selectByExample(FeedbackStatusExample example);

    FeedbackStatus selectByPrimaryKey(Integer feedbackStatusId);

    int updateByExampleSelective(@Param("record") FeedbackStatus record, @Param("example") FeedbackStatusExample example);

    int updateByExample(@Param("record") FeedbackStatus record, @Param("example") FeedbackStatusExample example);

    int updateByPrimaryKeySelective(FeedbackStatus record);

    int updateByPrimaryKey(FeedbackStatus record);
}