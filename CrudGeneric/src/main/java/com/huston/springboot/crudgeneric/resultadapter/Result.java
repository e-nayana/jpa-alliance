package com.huston.springboot.crudgeneric.resultadapter;

import com.huston.springboot.crudgeneric.GenericCrudEntity;

public class Result<T extends GenericCrudEntity> {


    Iterable<T> results;

    public Iterable<T> getResults() {
        return results;
    }

    public void setResults(Iterable<T> results) {
        this.results = results;
    }
}
