package com.cqjtu.service;

import com.cqjtu.model.PreOrders;
import java.util.List;

/**
 * @description: ${description}
 * @author: codezhang
 * @date: 2018-06-08 09:51
 **/
public interface PreOrderService {


    int insert(PreOrders preOrders);

    int updateCancleTime(PreOrders preOrders);


    int updateFinishTime(PreOrders preOrders);


    List<PreOrders>   select(PreOrders preOrders);


    boolean exitsDoctor(String doctorId);


}
