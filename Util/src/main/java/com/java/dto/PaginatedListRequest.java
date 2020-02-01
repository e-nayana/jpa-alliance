package com.java.dto;

import java.util.HashMap;

public class PaginatedListRequest {
    private Integer perPage = 10;
    private Integer page = 1;
    private HashMap<String,Object> filter = new HashMap<>();

    public Integer getPerPage() {
        return perPage;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public HashMap<String, Object> getFilter() {
        return filter;
    }

    public void setFilter(HashMap<String, Object> filter) {
        this.filter = filter;
    }
}
