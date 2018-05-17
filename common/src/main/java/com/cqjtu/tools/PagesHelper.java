package com.cqjtu.tools;

import com.cqjtu.domain.PageInfo;
import com.cqjtu.service.Pageable;

import java.util.Map;

/**
 * @author mevur
 * @date 18/1/30
 **/
public class PagesHelper {
    public static PageInfo getPageInfo(String table, Integer limit,
                                       Pageable mapper, Map<String, Object> param) {
        Integer count = mapper.count(param);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setCount(count);
        pageInfo.setTableName(table);
        Integer pageCount = count % limit == 0 ? count / limit : (count / limit) + 1;
        pageInfo.setPageCount(pageCount);
        pageInfo.setPerPage(limit);
        return pageInfo;
    }
}
