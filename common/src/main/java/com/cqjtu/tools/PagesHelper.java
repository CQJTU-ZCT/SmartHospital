package com.cqjtu.tools;

import com.cqjtu.domain.PageInfo;
import com.cqjtu.service.Pageable;

/**
 * @author mevur
 * @date 18/1/30
 **/
public class PagesHelper {
    public static PageInfo getPageInfo(String table, Integer limit, Pageable mapper) {
        Integer count = mapper.count();
        PageInfo pageInfo = new PageInfo();
        pageInfo.setCount(count);
        pageInfo.setTableName(table);
        Integer pageCount = count % limit == 0 ? count / limit : (count / limit) + 1;
        pageInfo.setPageCount(pageCount);
        pageInfo.setPerPage(limit);
        return pageInfo;
    }
}
