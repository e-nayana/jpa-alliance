package com.java.order;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class ControllerRequestCriteria {

    private Integer total;
    private Integer currentPage;
    private Integer perPage;
    private String searchCriteria;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }

    public Map<String, String> getSearchCriteria() throws JsonProcessingException, JsonParseException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = new HashMap<>();


        if(this.searchCriteria !=null && !"".equals(this.searchCriteria)){
            String json = this.searchCriteria;
            map = mapper.readValue(json, Map.class);
        }
        return map;
    }

    public void setSearchCriteria(String searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    @Override
    public String toString() {
        return "ControllerRequestCriteria{" +
                "total=" + total +
                ", currentPage=" + currentPage +
                ", perPage=" + perPage +
                ", searchCriteria=" + searchCriteria +
                '}';
    }
}
