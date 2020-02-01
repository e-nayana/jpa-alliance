package com.huston.springboot.crudgeneric.pagination;

import org.springframework.data.domain.Page;

import java.util.List;

public class ResultPage {

    private List<?> result;
    private Long total;
    private Integer currentPage;
    private Integer perPage;

    private ResultPage(ResultPageBuilder resultPageBuilder) {
        this.result = resultPageBuilder.result;
        this.total = resultPageBuilder.total;
        this.currentPage = resultPageBuilder.currentPage;
        this.perPage = resultPageBuilder.perPage;
    }

    public List<?> getResult() {
        return result;
    }

    public Long getTotal() {
        return total;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public static class ResultPageBuilder{
        private List<?> result;
        private Long total;
        private Integer currentPage;
        private Integer perPage;

        public ResultPageBuilder(Page<?> result) {
            this.result = result.getContent();
            this.total = result.getTotalElements();
            this.currentPage = result.getNumber()+1;
            this.perPage = result.getPageable().getPageSize();
        }

        public List<?> getResult() {
            return result;
        }

        public void setResult(List<?> result) {
            this.result = result;
        }

        public Long getTotal() {
            return total;
        }

        public void setTotal(Long total) {
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

        public ResultPage build(){
            return new ResultPage(this);
        }
    }
}
