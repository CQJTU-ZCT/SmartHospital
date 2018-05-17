package com.cqjtu.preorderprovider.service;

import com.cqjtu.mapperexp.PreorderProviderMapperExp;
import com.cqjtu.model.PreOrders;
import com.cqjtu.service.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author mevur
 * @date 18/3/1
 **/
@Service
public class PreorderProviderServiceImpl implements PreorderProviderService, Pageable {

    @Autowired
    PreorderProviderMapperExp mapper;

    @Override
    public Integer count(Map<String, Object> params) {
        return mapper.count(params);
    }

    @Override
    public Integer update(PreOrders preorders) {
        return mapper.update(preorders);
    }

    @Override
    public List<PreOrders> get(Map<String, Object> params, Integer page, Integer limit) {
        if (null == page) {
            page = 1;
        }
        if (null == limit) {
            limit = 20;
        }
        return mapper.get(params, (page - 1) * limit, limit);
    }

    @Override
    public PreOrders getById(String orderId) {
        return mapper.getById(orderId);
    }

    @Override
    public Integer deleteById(String orderId) {
        return mapper.deleteById(orderId);
    }

    @Override
    public Integer insert(PreOrders preOrders) {
        return mapper.insert(preOrders);
    }
}
