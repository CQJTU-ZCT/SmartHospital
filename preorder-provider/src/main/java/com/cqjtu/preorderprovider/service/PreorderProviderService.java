package com.cqjtu.preorderprovider.service;

import com.cqjtu.model.PreOrders;

import java.util.List;
import java.util.Map;

/**
 * @author mevur
 * @date 18/3/1
 **/
public interface PreorderProviderService {

    Integer count(Map<String, Object> params);

    Integer update(PreOrders preorders);

    List<PreOrders> get(Map<String, Object> params, Integer page, Integer limit);

    PreOrders getById(String orderId);

    Integer deleteById(String orderId);

    Integer insert(PreOrders preOrders);
}
