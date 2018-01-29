package com.cqjtu.domain;

import javax.validation.constraints.NotNull;

/**
 * @author mevur
 * @date 18/1/29
 **/
public class PaperInfo {
    private String tableName;
    private Integer pageCount;
    private Integer perPage;
    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }

    public boolean needToReget(Integer limit) {
        if (limit == this.perPage) {
            return false;
        } else {
            return true;
        }
    }

}
