package com.huston.springboot.crudgeneric.resultadapter;


import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;

public class AdapterImpl<T> extends Result implements Adapter{

    @Override
    public List<T> getList(){
        return Lists.newArrayList(getResults());
    }

    @Override
    public Set<T> getSet(){
        return Sets.newHashSet(getResults());
    }
}
