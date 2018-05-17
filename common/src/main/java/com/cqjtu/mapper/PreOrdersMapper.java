package com.cqjtu.mapper;

import com.cqjtu.model.PreOrders;
import com.cqjtu.model.PreOrdersExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PreOrdersMapper {
    long countByExample(PreOrdersExample example);

    int deleteByExample(PreOrdersExample example);

    int deleteByPrimaryKey(String orderId);

    int insert(PreOrders record);

    int insertSelective(PreOrders record);

    List<PreOrders> selectByExample(PreOrdersExample example);

    PreOrders selectByPrimaryKey(String orderId);

    int updateByExampleSelective(@Param("record") PreOrders record, @Param("example") PreOrdersExample example);

    int updateByExample(@Param("record") PreOrders record, @Param("example") PreOrdersExample example);

    int updateByPrimaryKeySelective(PreOrders record);

    int updateByPrimaryKey(PreOrders record);
}