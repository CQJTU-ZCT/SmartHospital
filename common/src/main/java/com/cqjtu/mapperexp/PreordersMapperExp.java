package com.cqjtu.mapperexp;

import com.cqjtu.model.PreOrders;

import java.util.List;

/**
 * @author mevur
 * @date 18/3/1
 **/
public interface PreordersMapperExp {

    int insert(PreOrders preOrders);

    int updateCancleTime(PreOrders preOrders);


    int updateFinishTime(PreOrders preOrders);


    List<PreOrders>   select(PreOrders preOrders);

}
