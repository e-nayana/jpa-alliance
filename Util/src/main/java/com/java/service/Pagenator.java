package com.java.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public class Pagenator {

    public static PageRequest setPagination(int page, int size){
        return PageRequest.of(page <= 0 ? 0 : (page -1),size);
    }

    public static PagenatedObject setMetaData(Page<?> result){
        PagenatedObject pagenatedObject = new PagenatedObject();
        pagenatedObject.setResult(result.getContent());
        pagenatedObject.setCurrentPage(result.getNumber()+1);
        pagenatedObject.setPerPage(result.getNumberOfElements());
        pagenatedObject.setTotal(result.getTotalElements());
        return pagenatedObject;
    }


    public static class PagenatedObject{
        List<?> result;
        long total;
        int currentPage;
        int perPage;


        public List<?> getResult() {
            return result;
        }

        public void setResult(List<?> result) {
            this.result = result;
        }

        public long getTotal() {
            return total;
        }

        public void setTotal(long total) {
            this.total = total;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getPerPage() {
            return perPage;
        }

        public void setPerPage(int perPange) {
            this.perPage = perPange;
        }
    }
}
