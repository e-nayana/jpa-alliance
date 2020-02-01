package com.huston.springboot.crudgeneric;

import javax.persistence.criteria.Predicate;
import java.util.List;

public interface QueryProcessor {


    String whereFilterArrayToString(List<String> whereFilters);

    Predicate[] predicateListToArgumentList(List<Predicate> predicates);
}
