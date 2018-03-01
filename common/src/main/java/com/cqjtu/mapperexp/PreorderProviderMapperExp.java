package com.cqjtu.mapperexp;

import com.cqjtu.model.PreOrders;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author mevur
 * @date 18/3/1
 **/
public interface PreorderProviderMapperExp {

    Integer insert(PreOrders preOrders);

    PreOrders getById(@Param("orderId") String orderId);

    Integer deleteById(@Param("orderId") String orderId);

    Integer update(PreOrders preOrders);

    List<PreOrders> get(@Param("param") Map<String, Object> param,
                        @Param("page") Integer page,
                        @Param("limit") Integer limit);

    Integer count(@Param("param") Map<String, Object> param);


}
