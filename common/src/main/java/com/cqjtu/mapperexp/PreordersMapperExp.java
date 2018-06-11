package com.cqjtu.mapperexp;

import com.cqjtu.model.PreOrders;
import com.cqjtu.modelexp.PreorderExp;

import java.util.List;

/**
 * @author mevur
 * @date 18/3/1
 **/
public interface PreordersMapperExp {

    int insert(PreOrders preOrders);

    int updateCancleTime(PreOrders preOrders);


    int updateFinishTime(PreOrders preOrders);


    List<PreorderExp>   select(PreOrders preOrders);

}
