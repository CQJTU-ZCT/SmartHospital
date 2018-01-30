package com.cqjtu.service;

/**
 * @author mevur
 * @date 18/1/30
 **/
public interface Pageable {
    /**
     * 查询数据库表中纪录数量
     * @return
     */
    Integer count();
}
