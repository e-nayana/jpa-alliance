package com.huston.springboot.crudgeneric;

import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.Iterator;
import java.util.List;

@Service
public class QueryProcessorImpl implements QueryProcessor{

    @Override
    public String whereFilterArrayToString(List<String> whereFilters){
        if(whereFilters.size() == 0){
            return "";
        }
        String queryString = " WHERE ";
        String whereFiltersAsString = "";
        Iterator<String> iterator = whereFilters.iterator();
        while (iterator.hasNext()) {
            String filter = iterator.next();
            whereFiltersAsString = whereFiltersAsString + filter + " ";
            if (iterator.hasNext()) {
                whereFiltersAsString = whereFiltersAsString + " AND ";
            }
        }
        queryString = queryString + whereFiltersAsString;
        return queryString;
    }

    @Override
    public Predicate[] predicateListToArgumentList(List<Predicate> predicates){
        Predicate[] predicateArgumentList = new javax.persistence.criteria.Predicate[predicates.size()];
        for(int i = 0; i < predicates.size(); i++) {
            predicateArgumentList[i] = predicates.get(i);
        }
        return predicateArgumentList;
    }
}
