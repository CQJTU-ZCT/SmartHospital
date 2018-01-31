package com.cqjtu.service;

import java.util.Map;

/**
 * @author mevur
 * @date 18/1/30
 **/
public interface Pageable {
    /**
     * 查询数据库表中纪录数量
     * @return
     */
    Integer count(Map<String, Object> param);
}
